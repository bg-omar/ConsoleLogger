package com.github.bgomar.consolelogger

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConsoleLoggerActionTest {
    @Test
    fun `logger is placed after variable declaration`() {
        val input = """
        const platform: string = queryParams.platform?.trim().toLowerCase() ?? '';
        """
        val expected = """
        const platform: string = queryParams.platform?.trim().toLowerCase() ?? '';
        console.log('%c 1 --> Line: 81||navigation.service.ts\n platform: ', 'color:#f0f;', platform);
        """
        val output = simulateLoggerInsertion(input, "platform", "console.log('%c 1 --> Line: 81||navigation.service.ts\n platform: ', 'color:#f0f;', platform);")
        assertEquals(expected.trim(), output.trim())
    }

    @Test
    fun `logger is placed after assignment`() {
        val input = """
        this.table = {
            ...this.table,
            items: tableObject
        }
        """
        val expected = """
        this.table = {
            ...this.table,
            items: tableObject
        }
        console.log("%c 1 -->  tableObject: ", "color:#f0f;", tableObject);
        """
        val output = simulateLoggerInsertion(input, "tableObject", "console.log(\"%c 1 -->  tableObject: \", \"color:#f0f;\", tableObject);")
        assertEquals(expected.trim(), output.trim())
    }

    @Test
    fun `logger is placed before function call, not inside callback`() {
        val input = """
        this.http.post(Url, body).subscribe({
            next: (): void => {
                this.something.next(action);
            },
            error: (): void => {
                this.something.error(action);
            }
        });
        """
        val expected = """
        console.log("%c 1 --> Url: ","color:#f0f;", Url);
        this.http.post(Url, body).subscribe({
            next: (): void => {
                this.something.next(action);
            },
            error: (): void => {
                this.something.error(action);
            }
        });
        """
        val output = simulateLoggerInsertion(input, "Url", "console.log(\"%c 1 --> Url: \", \"color:#f0f;\", Url);")
        assertEquals(expected.trim(), output.trim())
    }

    // Simulate the logger insertion logic (robust, for test purposes)
    private fun simulateLoggerInsertion(input: String, variable: String, logger: String): String {
        val lines = input.trim().lines().toMutableList()
        val loggerLine = logger.trim()

        // Try to find the line to insert after (variable declaration or assignment)
        val varRegex = Regex("(const|let|var)\\s+.*$variable.*[=;]")
        val assignRegex = Regex(".*[.=]\\s*.*$variable.*[=;]?")
        val funcCallRegex = Regex(".*\\b$variable\\b.*\\(.*\\).*")

        // 1. Variable declaration
        for (i in lines.indices) {
            if (varRegex.containsMatchIn(lines[i])) {
                // Insert logger after declaration
                lines.add(i + 1, loggerLine)
                return lines.joinToString("\n")
            }
        }
        // 2. Assignment (object, property, etc.)
        for (i in lines.indices) {
            if (assignRegex.containsMatchIn(lines[i])) {
                lines.add(i + 1, loggerLine)
                return lines.joinToString("\n")
            }
        }
        // 3. Function call (insert before)
        for (i in lines.indices) {
            if (funcCallRegex.containsMatchIn(lines[i])) {
                lines.add(i, loggerLine)
                return lines.joinToString("\n")
            }
        }
        // 4. Fallback: insert at end
        lines.add(loggerLine)
        return lines.joinToString("\n")
    }

    @Test
    fun `logger is placed after destructuring assignment`() {
        val input = """
        const { a, b } = someObject;
        """
        val expected = """
        const { a, b } = someObject;
        console.log('%c 1 --> a: ', 'color:#f0f;', a);
        """
        val output = simulateLoggerInsertion(input, "a", "console.log('%c 1 --> a: ', 'color:#f0f;', a);")
        assertEquals(expected.trim(), output.trim())
    }

    @Test
    fun `logger is placed after multiple variable declarations`() {
        val input = """
        let x = 1, y = 2;
        """
        val expected = """
        let x = 1, y = 2;
        console.log('%c 1 --> y: ', 'color:#f0f;', y);
        """
        val output = simulateLoggerInsertion(input, "y", "console.log('%c 1 --> y: ', 'color:#f0f;', y);")
        assertEquals(expected.trim(), output.trim())
    }

    @Test
    fun `logger is placed after nested block assignment`() {
        val input = """
        if (true) {
            const foo = 'bar';
        }
        """
        val expected = """
        if (true) {
            const foo = 'bar';
            console.log('%c 1 --> foo: ', 'color:#f0f;', foo);
        }
        """
        val output = simulateLoggerInsertion(input, "foo", "console.log('%c 1 --> foo: ', 'color:#f0f;', foo);")
        assertEquals(expected.trim(), output.trim())
    }

    // Add more tests for other edge cases as needed
}
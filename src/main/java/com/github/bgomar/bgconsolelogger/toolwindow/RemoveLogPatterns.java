package com.github.bgomar.bgconsolelogger.toolwindow;

import com.intellij.find.FindModel;
import com.intellij.find.FindUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

import java.util.regex.Pattern;

public class RemoveLogPatterns extends AnAction {

    /**
     * Action method called when the action is performed.
     * This method triggers the regex-based log removal in the editor.
     */
    @Override
    public void actionPerformed(AnActionEvent e) {
        // Get the project and editor from the context
        Project project = e.getData(CommonDataKeys.PROJECT);
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();

        if (project == null) {
            System.out.println("No active project found.");
            return;
        }

        if (editor == null) {
            System.out.println("No active editor found.");
            return;
        }

        // Generate the regex pattern for log removal
        String logPattern = "console.log(\"%c 18 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";
        String regexPattern = generateRemovePattern(logPattern);

        System.out.println("Generated Regex: " + regexPattern);

        // Test the pattern on the editor text
        String editorText = editor.getDocument().getText();  // Get the text from the active editor
        if (Pattern.matches(regexPattern, editorText)) {
            System.out.println("Log matched in the editor!");
            removeLogs(project, editor, regexPattern);  // Call to remove logs if matched
        } else {
            System.out.println("Log not matched in the editor.");
        }
    }

    public static String generateRemovePattern(String pattern) {
        // Escape special characters in the pattern and replace placeholders
        return ".*" + escapeSpecialCharacters(pattern)
                .replace("{LN}", "\\\\d+")      // Replace {LN} with regex for digits (line numbers)
                .replace("{FN}", ".*")          // Replace {FN} with regex for any filename
                .replace("\\n", "\\\\n")        // Properly escape newline characters
                .replace("$$", ".*")            // Handle dynamic content represented by $$
                + ".*";                         // Ensure the regex matches the entire line
    }

    /**
     * Escapes special regex characters in the pattern string.
     */
    private static String escapeSpecialCharacters(String str) {
        return str.replace("\\", "\\\\")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("^", "\\^")
                .replace("+", "\\+")
                .replace("?", "\\?")
                .replace("|", "\\|")
                .replace(".", "\\.")
                .replace("*", "\\*")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("$", "\\$");
    }



    /**
     * Remove logs based on the regex pattern within the active editor.
     */
    private void removeLogs(Project project, Editor editor, String regexPattern) {
        // Create the FindModel for the regex pattern
        FindModel findModel = new FindModel();
        findModel.setStringToFind(regexPattern);     // Set the regex pattern for finding
        findModel.setGlobal(true);                   // Search globally in the editor
        findModel.setRegularExpressions(true);       // Use regex for search
        findModel.setStringToReplace("");            // We are removing logs, so replace with an empty string
        findModel.setPromptOnReplace(false);         // Do not prompt for each replacement

        // Use FindUtil to find and replace logs based on the pattern
        FindUtil.replace(project, editor, 0, findModel);
        System.out.println("Logs removed based on pattern: " + regexPattern);
    }

    /**
     * Main method for testing outside the context of IntelliJ plugin.
     */
    public static void main(String[] args) {
        // Example usage
        String logPattern = "console.log(\"%c 18 --> {LN}||{FN}\\n $$: \",\"color:#acf;\", $$);";

        String regexPattern = generateRemovePattern(logPattern);

        System.out.println("Generated Regex: " + regexPattern);

        // Test the generated regex
        String testLog = "console.log(\"%c 18 --> 42||myfile.js\\n some text: \",\"color:#acf;\", someVariable);";

        boolean matches = Pattern.matches(regexPattern, testLog);

        if (matches) {
            System.out.println("Log matched!");
        } else {
            System.out.println("Log not matched.");
        }
    }
}

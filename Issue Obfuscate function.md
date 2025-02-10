

```typescript
getPcName(buttenName: string): string {
const pcMatch = buttenName.match(/\d+\.\d+\.\d+\.(\d+)/);  // Matches the last octet
const portMatch = buttenName.match(/:(\d{4})/);  // Matches the first two digits of the port (81)
// Extract values if matches are found
const pc = pcMatch ? pcMatch[1] : null;
const port = portMatch ? portMatch[1].slice(-2) : null;

    let matchedText;
    if (pc && port) {
      matchedText = pc + ': ' + port;
    }
    return matchedText;
}
```
```typescript
Function: getPcName
Parameters:
buttenName: string

Function Body:
{
const pcMatch = buttenName.match(/\d+\.\d+\.\d+\.(\d+)/);  // Matches the last octet
const portMatch = buttenName.match(/:(\d{4})/);  // Matches the first two digits of the port (81)
// Extract values if matches are found
const pc = pcMatch ? pcMatch[1] : null;
const port = portMatch ? portMatch[1].slice(-2) : null;

    let matchedText;
    if (pc && port) {
      matchedText = pc + ': ' + port;
    }
    return matchedText;
}
```
```typescript
function1(arg1: string): void {
{
const var1 = buttenName.match(/\d+\.\d+\.\d+\.(\d+)/);  // Matches the last octet
const var2 = buttenName.match(/:(\d{4})/);  // Matches the first two digits of the port (81)
// Extract values if matches are found
const var3 = pcMatch ? pcMatch[1] : null;
const var4 = portMatch ? portMatch[1].slice(-2) : null;

    let var5;
    if (pc && port) {
    matchedText = pc + ': ' + port;
    }
    return matchedText;
    }
}

```
```typescript
function1(arg1: string): void {
    {
    const var1 = buttenName.match(/\d+\.\d+\.\d+\.(\d+)/);  // Matches the last octet
    const var2 = buttenName.match(/:(\d{4})/);  // Matches the first two digits of the port (81)
    // Extract values if matches are found
    const var3 = pcMatch ? pcMatch[1] : null;
    const var4 = portMatch ? portMatch[1].slice(-2) : null;
    
    let var5;
    if (pc && port) {
    matchedText = pc + ': ' + port;
    }
    return matchedText;
    }
}
```
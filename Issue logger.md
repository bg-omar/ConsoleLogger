
This is a guide to correctly place loggers in TypeScript code to avoid issues with undefined variables.
# Correct Logger Placement in TypeScript

When adding loggers to your TypeScript code, it's crucial to place them correctly to avoid referencing variables before they are defined. Below are examples of both correct and incorrect placements of loggers.

Adjust this file to demonstrate correct and incorrect logger placements for future versions of the plugin.

Check the status at the top of each example to see if the logger placement is correct (✅) or incorrect (❌).

# Adding Issue of Incorrect Logger Placement
1. state where the logger is used on and add a new sample code below.
2. state the location the logger is placed incorrectly in the code example
   console.log('WRONG log location, used [before/within/after] defined [variableName]: ', variableName);
3. state the location the logger is placed correctly in the code example
   console.log('CORRECT log location, used [before/within/after] defined [variableName]: ', variableName);

---
Status: ✅
Running inside fetshData() on a this.http.post(Url) on `Url` :
```typescript
fetshData(Url, body): void {
	console.log('CORRECT log location, used within this.http.post(Url, body){}: ', Url);

	this.http.post(Url, body).subscribe({
		console.log('WRONG log location, used within this.http.post(Url, body){}: ', Url);
		next: (): void => {
			this.something.next(action);
		},
		error: (): void => {
			this.something.error(action);
		}
	});
}
```
---
Status: ❌
Running inside an object on  `tableObject` :
```typescript
    console.log('CORRECT log location, used before defined object this.table = {} : ', tableObject);
    this.table = {
        ...this.table,
        items: tableObject
            console.log("STILL WRONG tableObject: ", tableObject);
			console.log('WRONG log location, used within defined object this.table = {} tableObject: ', tableObject);
		}
```
---
Status: ✅,✅,✅
Running inside a function on 1: const `platform`, 2: const `accountIds` and 3: `this.platform`, :
```typescript

    console.log('1: WRONG log location, used before defined platform: ', platform); 
    const platform: string = queryParams.platform?.trim().toLowerCase() ?? '';
    console.log('1: CORRECT log location, used after defined platform: ', platform);
		
		
    console.log('2: WRONG log location, used before defined accountIds', accountIds);
    const accountIds: string = queryParams.accountId?.trim() ?? '';
    console.log('2: CORRECT log location, used after defined accountIds', accountIds);
		
		
    if (this.isValidPlatform(platform)) {
      console.log('3: CORRECT log location, used within if statement after defined platform', platform); 
      this.platform = this.resolvePlatform(platform);
    }
```
---
Status: ❌
running inside a try/catch on `error` :
```typescript
    try { 
        console.log('WRONG log location, used in try, before defined error: ', error);
        console.log(" STILL WRONG error: ", error);
    } catch (error) {
	  console.log('CORRECT log location, used after defined error inside catch: ', error);
      this.accounts = [];
    }
```
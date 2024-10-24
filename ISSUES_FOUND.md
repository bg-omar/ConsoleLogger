
```typescript
    console.log("%c 1 --> Url: ","color:#f0f;", Url);  // <--- correct
    this.http.post(Url, body).subscribe({
        // console.log("%c 1 --> Url: ","color:#f0f;", Url);   <-----wrong should be 2 lines up
        next: (): void => {
            this.something.next(action);
        },
        error: (): void => {
            this.something.error(action);
        }
    });
```

```typescript
    console.log("%c 1 -->  tableObject: ","color:#f0f;", tableObject);  // <--- correct
    this.table = {
        ...this.table,
        items: tableObject
        //     console.log("%c 1 -->  tableObject: ","color:#f0f;", tableObject); <--- wrong
    }
```
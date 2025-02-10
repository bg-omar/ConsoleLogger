
The original class
```typescript
export class TradebotComponent implements OnInit, OnChanges, OnDestroy {
    static hideIframe: string;
    @Input() tradebotdata: User[]=  [];
    all: any;
    routerSubscription: any;
    apiKey: string = '';
    secretKey: string = '';
    budget: number = 0.0001;
    tradingPairs: any[] = [];
    orders: any[] = [];
    excludeList: string[] = [];
    log: string[] = [];

    constructor(
        public popoverCtrl: PopoverController,
        public loadingCtrl: LoadingController,
        public storageServive: StorageService,
        public router: Router,
        public routerOutlet: IonRouterOutlet,
        public config: Config,
        private renderer: Renderer2,
        public alertCtrl: AlertController,
        public confData: ConferenceData,
        public modalCtrl: ModalController,
        public toastCtrl: ToastController,
        public user: UserData,
        private http: HttpClient,
        private alertController: AlertController,
        private toastController: ToastController

    ) { }

    // This lifecycle method will be triggered when the Home page becomes active
    ionViewWillEnter() {
        this.refreshData(); // Replace this with your data-fetching or refreshing logic
    }

    refreshData() {
        this.getJson();
    }

    toggle($event: User) {
        this.tradebotdata.find(value => {
            if (value.ipAddress === $event.ipAddress) value.hide = !value.hide;
        })
        console.log("$event: ", $event);
    }

    async getJson() {
        await this.storageServive.getData("TRADEBOT").then((data:any) => {
            let res: User[];
            res = JSON.parse(data.value);
            this.tradebotdata = res
            console.log("%c 2 --> 73||C:/workspace/projects/Android-tradebot/src/app/pages/tradebot/tradebot.component.ts\n this.tradebotdata: ","color:#0f0;", this.tradebotdata);
        });
    }

    async ngOnInit(): Promise<void> {
        await this.getJson();
        this.loadTradingPairs();
    }

    // This method will be triggered whenever @Input() properties change
    ngOnChanges(changes: SimpleChanges) {
        console.log('Changes detected:', changes);

        // Check if a specific property changed
        if (changes['tradebotdata']) {
            const previousValue = changes['tradebotdata'].previousValue;
            const currentValue = changes['tradebotdata'].currentValue;
            console.log(`inputProp changed from ${previousValue} to ${currentValue}`);

        }
    }

    ngOnDestroy(): void {
        // Unsubscribe to avoid memory leaks

    }

    loadTradingPairs() {
        this.http.get('http://localhost:3000/api/trading-pairs').subscribe(
            (data: any) => {
                this.tradingPairs = data;
            },
            (error) => {
                console.error('Error fetching trading pairs', error);
            }
        );
    }

    startBot() {
        this.http.post('http://localhost:3000/api/start', {
            apiKey: this.apiKey,
            secretKey: this.secretKey,
            budget: this.budget,
            excludeList: this.excludeList,
        }).subscribe(
            async (res) => {
                const toast = await this.toastController.create({
                    message: 'TradeBot started successfully!',
                    duration: 2000,
                    color: 'success',
                });
                await toast.present();
            },
            async (error) => {
                const alert = await this.alertController.create({
                    header: 'Error',
                    message: 'Failed to start the TradeBot.',
                    buttons: ['OK'],
                });
                await alert.present();
            }
        );
    }

    stopBot() {
        this.http.post('http://localhost:3000/api/stop', {}).subscribe(
            async (res) => {
                const toast = await this.toastController.create({
                    message: 'TradeBot stopped.',
                    duration: 2000,
                    color: 'danger',
                });
                await toast.present();
            }
        );
    }

    getOrders() {
        this.http.get('http://localhost:3000/api/orders').subscribe(
            (data: any) => {
                this.orders = data;
            },
            (error) => {
                console.error('Error fetching orders', error);
            }
        );
    }



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

    async presentPopover(event: Event) {
        const popover = await this.popoverCtrl.create({
            component: TradebotPopoverPage,
            event
        });
        await popover.present();
    }
}

```

FunctionExtractorAction.java result:
```text
Class: TradebotComponent
Methods:
    constructor
ionViewWillEnter
refreshData
toggle
getJson
ngOnInit
ngOnChanges
ngOnDestroy
loadTradingPairs
startBot
stopBot
getOrders
getPcName
presentPopover

```

FunctionExtractorActionKT.kt result:
```typescript
class function1 {
    var1: string;
    var2: type1;
    var3: type2;
    var4: type3;
    var5: string;
    var6: string;
    var7: number;
    var8: type4;
    var9: type5;
    var10: type6;
    var11: type7;
    function2(arg1: type8, arg2: type9, arg3: type10, arg4: type11, arg5: type12, arg6: type13, arg7: type14, arg8: type15, arg9: type16, arg10: type17, arg11: type18, arg12: type19, arg13: type20, arg14: type21, arg15: type22): void {
        { }
    }
    function3(): void {
        {
            this.prop1(); // Replace this with your data-fetching or refreshing logic
        }
    }
    function4(): void {
        {
            this.prop2();
        }
    }
    function5(arg31: type23): void {
        {
            this.prop3.find(value => {
                if (value.ipAddress === $event.ipAddress) value.hide = !value.hide;
            })
            console.log("$event: ", $event);
        }
    }
    function6(): void {
        {
            await this.prop4.getData("TRADEBOT").then((data:any) => {
                let var12: User[];
                res = JSON.parse(data.value);
                this.prop5 = res
                console.log("%c 2 --> 73||C:/workspace/projects/Android-tradebot/src/app/pages/tradebot/tradebot.component.ts\n this.prop6: ","color:#0f0;", this.prop7);
            });
        }
    }
    function7(): void {
        {
            await this.prop8();
            this.prop9();
        }
    }
    function8(arg33: type24): void {
        {
            console.log('Changes detected:', changes);

            // Check if a specific property changed
            if (changes['tradebotdata']) {
                const var13 = changes['tradebotdata'].previousValue;
                const var14 = changes['tradebotdata'].currentValue;
                console.log(`inputProp changed from ${previousValue} to ${currentValue}`);

            }
        }
    }
    function9(): void {
        {
            // Unsubscribe to avoid memory leaks

        }
    }
    function10(): void {
        {
            this.prop10.get('http://localhost:3000/api/trading-pairs').subscribe(
                (data: any) => {
                    this.prop11 = data;
                },
                (error) => {
                    console.error('Error fetching trading pairs', error);
                }
            );
        }
    }
    function11(): void {
        {
            this.prop12.post('http://localhost:3000/api/start', {
                apiKey: this.prop13,
                secretKey: this.prop14,
                budget: this.prop15,
                excludeList: this.prop16,
            }).subscribe(
                async (res) => {
                    const var15 = await this.prop17.create({
                        message: 'TradeBot started successfully!',
                        duration: 2000,
                        color: 'success',
                    });
                    await toast.present();
                },
                async (error) => {
                    const var16 = await this.prop18.create({
                        header: 'Error',
                        message: 'Failed to start the TradeBot.',
                        buttons: ['OK'],
                    });
                    await alert.present();
                }
            );
        }
    }
    function12(): void {
        {
            this.prop19.post('http://localhost:3000/api/stop', {}).subscribe(
                async (res) => {
                    const var15 = await this.prop20.create({
                        message: 'TradeBot stopped.',
                        duration: 2000,
                        color: 'danger',
                    });
                    await toast.present();
                }
            );
        }
    }
    function13(): void {
        {
            this.prop21.get('http://localhost:3000/api/orders').subscribe(
                (data: any) => {
                    this.prop22 = data;
                },
                (error) => {
                    console.error('Error fetching orders', error);
                }
            );
        }
    }
    function14(arg35: string): void {
        {
            const var17 = buttenName.match(/\d+\.\d+\.\d+\.(\d+)/);  // Matches the last octet
            const var18 = buttenName.match(/:(\d{4})/);  // Matches the first two digits of the port (81)
            // Extract values if matches are found
            const var19 = pcMatch ? pcMatch[1] : null;
            const var20 = portMatch ? portMatch[1].slice(-2) : null;

            let var21;
            if (pc && port) {
                matchedText = pc + ': ' + port;
            }
            return matchedText;
        }
    }
    function15(arg37: type25): void {
        {
            const var22 = await this.prop23.create({
                component: TradebotPopoverPage,
                event
            });
            await popover.present();
        }
    }
}

```

FunctionExtractorClass.java result:
```typescript
class function1 {
    function2() {}
    function3() {}
    function4() {}
    function5() {}
    function6() {}
    function7() {}
    function8() {}
    function9() {}
    function10() {}
    function11() {}
    function12() {}
    function13() {}
    function14() {}
    function15() {}
}
```
package com.catchy.denzicam;

public class Tree {
    private String name;
    private int age;

    Tree(String name, int perimeter){
        amendTree(name, perimeter);
    }

    private void amendTree( String name, int perimeter){
        switch (name){
            case "Akác":
                this.name = name;
                if      (perimeter <= 33 )  this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 22;
                else if (perimeter < 127)   this.age = 30;
                else if (perimeter < 159)   this.age = 38;
                else if (perimeter < 190)   this.age = 46;
                else if (perimeter < 222)   this.age = 54;
                else if (perimeter < 253)   this.age = 62;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 80;
                break;
            case "Bálványfa":
                this.name = name;
                if      (perimeter <= 33 )  this.age = 45;
                else if (perimeter < 65)    this.age = 12;
                else if (perimeter < 96)    this.age = 18;
                else if (perimeter < 127)   this.age = 27;
                else if (perimeter < 159)   this.age = 35;
                else if (perimeter < 190)   this.age = 45;
                else if (perimeter < 222)   this.age = 50;
                else if (perimeter < 253)   this.age = 56;
                else if (perimeter < 284)   this.age = 65;
                else this.age = 72;
                break;
            case "Bükk":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 33;
                else if (perimeter < 159)   this.age = 40;
                else if (perimeter < 190)   this.age = 46;
                else if (perimeter < 222)   this.age = 52;
                else if (perimeter < 253)   this.age = 58;
                else if (perimeter < 284)   this.age = 65;
                else this.age = 75;
                break;
            case "Csertölgy":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 36;
                else if (perimeter < 159)   this.age = 44;
                else if (perimeter < 190)   this.age = 54;
                else if (perimeter < 222)   this.age = 63;
                else if (perimeter < 253)   this.age = 72;
                else if (perimeter < 284)   this.age = 80;
                else this.age = 85;
                break;
            case "Enyves éger":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 23;
                else if (perimeter < 127)   this.age = 31;
                else if (perimeter < 159)   this.age = 40;
                else if (perimeter < 190)   this.age = 48;
                else if (perimeter < 222)   this.age = 56;
                else if (perimeter < 253)   this.age = 64;
                else if (perimeter < 284)   this.age = 71;
                else this.age = 85;
                break;
            case "Ezüst hárs":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 33;
                else if (perimeter < 159)   this.age = 45;
                else if (perimeter < 190)   this.age = 55;
                else if (perimeter < 222)   this.age = 64;
                else if (perimeter < 253)   this.age = 70;
                else if (perimeter < 284)   this.age = 76;
                else this.age = 85;
                break;
            case "Fehér fűz":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 23;
                else if (perimeter < 127)   this.age = 30;
                else if (perimeter < 159)   this.age = 38;
                else if (perimeter < 190)   this.age = 46;
                else if (perimeter < 222)   this.age = 55;
                else if (perimeter < 253)   this.age = 63;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 76;
                break;
            case "Fehér nyár":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 23;
                else if (perimeter < 127)   this.age = 30;
                else if (perimeter < 159)   this.age = 36;
                else if (perimeter < 190)   this.age = 42;
                else if (perimeter < 222)   this.age = 47;
                else if (perimeter < 253)   this.age = 53;
                else if (perimeter < 284)   this.age = 60;
                else this.age = 65;
                break;
            case "Fehér vadgesztenye":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 13;
                else if (perimeter < 96)    this.age = 20;
                else if (perimeter < 127)   this.age = 26;
                else if (perimeter < 159)   this.age = 33;
                else if (perimeter < 190)   this.age = 40;
                else if (perimeter < 222)   this.age = 46;
                else if (perimeter < 253)   this.age = 52;
                else if (perimeter < 284)   this.age = 59;
                else this.age = 65;
                break;
            case "Hegyi juhar":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 14;
                else if (perimeter < 96)    this.age = 22;
                else if (perimeter < 127)   this.age = 30;
                else if (perimeter < 159)   this.age = 40;
                else if (perimeter < 190)   this.age = 48;
                else if (perimeter < 222)   this.age = 55;
                else if (perimeter < 253)   this.age = 62;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 80;
                break;
            case "Japán akác":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 33;
                else if (perimeter < 159)   this.age = 40;
                else if (perimeter < 190)   this.age = 47;
                else if (perimeter < 222)   this.age = 55;
                else if (perimeter < 253)   this.age = 64;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 75;
                break;
            case "Keskenylevelű ezüstfa":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 27;
                else if (perimeter < 127)   this.age = 35;
                else if (perimeter < 159)   this.age = 42;
                else if (perimeter < 190)   this.age = 50;
                else if (perimeter < 222)   this.age = 57;
                else if (perimeter < 253)   this.age = 65;
                else if (perimeter < 284)   this.age = 72;
                else this.age = 80;
                break;
            case "Kislevelű hárs":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 24;
                else if (perimeter < 127)   this.age = 32;
                else if (perimeter < 159)   this.age = 39;
                else if (perimeter < 190)   this.age = 47;
                else if (perimeter < 222)   this.age = 56;
                else if (perimeter < 253)   this.age = 64;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 76;
                break;
            case "Kocsányos tölgy":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 17;
                else if (perimeter < 96)    this.age = 27;
                else if (perimeter < 127)   this.age = 36;
                else if (perimeter < 159)   this.age = 46;
                else if (perimeter < 190)   this.age = 55;
                else if (perimeter < 222)   this.age = 65;
                else if (perimeter < 253)   this.age = 74;
                else if (perimeter < 284)   this.age = 82;
                else this.age = 90;
                break;
            case "Kocsánytalan tölgy":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 26;
                else if (perimeter < 127)   this.age = 37;
                else if (perimeter < 159)   this.age = 45;
                else if (perimeter < 190)   this.age = 53;
                else if (perimeter < 222)   this.age = 64;
                else if (perimeter < 253)   this.age = 71;
                else if (perimeter < 284)   this.age = 80;
                else this.age = 87;
                break;
            case "Korai juhar":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 12;
                else if (perimeter < 96)    this.age = 20;
                else if (perimeter < 127)   this.age = 28;
                else if (perimeter < 159)   this.age = 38;
                else if (perimeter < 190)   this.age = 45;
                else if (perimeter < 222)   this.age = 50;
                else if (perimeter < 253)   this.age = 58;
                else if (perimeter < 284)   this.age = 67;
                else this.age = 75;
                break;
            case "Közönséges dió":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 17;
                else if (perimeter < 96)    this.age = 28;
                else if (perimeter < 127)   this.age = 38;
                else if (perimeter < 159)   this.age = 47;
                else if (perimeter < 190)   this.age = 55;
                else if (perimeter < 222)   this.age = 64;
                else if (perimeter < 253)   this.age = 72;
                else if (perimeter < 284)   this.age = 80;
                else this.age = 87;
                break;
            case "Közönséges gyertyán":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 20;
                else if (perimeter < 96)    this.age = 30;
                else if (perimeter < 127)   this.age = 40;
                else if (perimeter < 159)   this.age = 50;
                else if (perimeter < 190)   this.age = 58;
                else if (perimeter < 222)   this.age = 65;
                else if (perimeter < 253)   this.age = 71;
                else if (perimeter < 284)   this.age = 76;
                else this.age = 84;
                break;
            case "Közönséges nyír":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 35;
                else if (perimeter < 159)   this.age = 45;
                else if (perimeter < 190)   this.age = 52;
                else if (perimeter < 222)   this.age = 60;
                else if (perimeter < 253)   this.age = 67;
                else if (perimeter < 284)   this.age = 75;
                else this.age = 85;
                break;
            case "Magas kőris":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 22;
                else if (perimeter < 127)   this.age = 28;
                else if (perimeter < 159)   this.age = 35;
                else if (perimeter < 190)   this.age = 42;
                else if (perimeter < 222)   this.age = 50;
                else if (perimeter < 253)   this.age = 58;
                else if (perimeter < 284)   this.age = 65;
                else this.age = 70;
                break;
            case "Magyar kőris":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 18;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 31;
                else if (perimeter < 159)   this.age = 38;
                else if (perimeter < 190)   this.age = 46;
                else if (perimeter < 222)   this.age = 53;
                else if (perimeter < 253)   this.age = 60;
                else if (perimeter < 284)   this.age = 66;
                else this.age = 75;
                break;
            case "Mezei juhar":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 40;
                else if (perimeter < 159)   this.age = 45;
                else if (perimeter < 190)   this.age = 50;
                else if (perimeter < 222)   this.age = 57;
                else if (perimeter < 253)   this.age = 65;
                else if (perimeter < 284)   this.age = 72;
                else this.age = 80;
                break;
            case "Mezei szil":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 24;
                else if (perimeter < 127)   this.age = 34;
                else if (perimeter < 159)   this.age = 41;
                else if (perimeter < 190)   this.age = 48;
                else if (perimeter < 222)   this.age = 56;
                else if (perimeter < 253)   this.age = 62;
                else if (perimeter < 284)   this.age = 68;
                else this.age = 75;
                break;
            case "Nagylevelű hárs":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 17;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 33;
                else if (perimeter < 159)   this.age = 40;
                else if (perimeter < 190)   this.age = 45;
                else if (perimeter < 222)   this.age = 50;
                else if (perimeter < 253)   this.age = 58;
                else if (perimeter < 284)   this.age = 65;
                else this.age = 70;
                break;
            case "Nyugati ostorfa":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 15;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 40;
                else if (perimeter < 159)   this.age = 48;
                else if (perimeter < 190)   this.age = 55;
                else if (perimeter < 222)   this.age = 66;
                else if (perimeter < 253)   this.age = 80;
                else if (perimeter < 284)   this.age = 90;
                else this.age = 96;
                break;
            case "Vadcseresznye":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 18;
                else if (perimeter < 96)    this.age = 26;
                else if (perimeter < 127)   this.age = 35;
                else if (perimeter < 159)   this.age = 43;
                else if (perimeter < 190)   this.age = 50;
                else if (perimeter < 222)   this.age = 55;
                else if (perimeter < 253)   this.age = 60;
                else if (perimeter < 284)   this.age = 65;
                else this.age = 70;
                break;
            case "Vénic szil":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 35;
                else if (perimeter < 159)   this.age = 42;
                else if (perimeter < 190)   this.age = 50;
                else if (perimeter < 222)   this.age = 57;
                else if (perimeter < 253)   this.age = 62;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 77;
                break;
            case "Virágos kőris":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 18;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 30;
                else if (perimeter < 159)   this.age = 36;
                else if (perimeter < 190)   this.age = 45;
                else if (perimeter < 222)   this.age = 58;
                else if (perimeter < 253)   this.age = 65;
                else if (perimeter < 284)   this.age = 72;
                else this.age = 75;
                break;
            case "Zöld juhar":
                this.name = name;
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 25;
                else if (perimeter < 127)   this.age = 35;
                else if (perimeter < 159)   this.age = 48;
                else if (perimeter < 190)   this.age = 60;
                else if (perimeter < 222)   this.age = 68;
                else if (perimeter < 253)   this.age = 75;
                else if (perimeter < 284)   this.age = 80;
                else this.age = 85;
                break;
            default :
                this.name = "Ismeretlen";
                if      (perimeter <= 33)   this.age = 45;
                else if (perimeter < 65)    this.age = 16;
                else if (perimeter < 96)    this.age = 24;
                else if (perimeter < 127)   this.age = 33;
                else if (perimeter < 159)   this.age = 41;
                else if (perimeter < 190)   this.age = 49;
                else if (perimeter < 222)   this.age = 56;
                else if (perimeter < 253)   this.age = 63;
                else if (perimeter < 284)   this.age = 70;
                else this.age = 77;
                break;
        }
    }

    public static float getOxygen( int avg ){
        switch(avg){
            case 12 : return 21.9f;
            case 13 : return 23.5f;
            case 14 : return 25.1f;
            case 15 : return 26.7f;
            case 16 : return 28.3f;
            case 17 : return 29.9f;
            case 18 : return 31.5f;
            case 19 : return 33.1f;
            case 20 : return 34.7f;
            case 21 : return 36.5f;
            case 22 : return 38.3f;
            case 23 : return 40.2f;
            case 24 : return 42.0f;
            case 25 : return 43.8f;
            case 26 : return 45.6f;
            case 27 : return 47.4f;
            case 28 : return 49.2f;
            case 29 : return 51.0f;
            case 30 : return 52.8f;
            case 31 : return 54.4f;
            case 32 : return 56.0f;
            case 33 : return 57.5f;
            case 34 : return 59.0f;
            case 35 : return 60.5f;
            case 36 : return 62.1f;
            case 37 : return 63.7f;
            case 38 : return 65.2f;
            case 39 : return 66.7f;
            case 40 : return 68.2f;
            case 41 : return 69.7f;
            case 42 : return 71.2f;
            case 43 : return 72.6f;
            case 44 : return 74.0f;
            case 45 : return 75.4f;
            case 46 : return 76.9f;
            case 47 : return 78.3f;
            case 48 : return 79.7f;
            case 49 : return 81.1f;
            case 50 : return 82.5f;
            case 51 : return 83.4f;
            case 52 : return 84.3f;
            case 53 : return 85.1f;
            case 54 : return 86.0f;
            case 55 : return 86.9f;
            case 56 : return 87.8f;
            case 57 : return 88.7f;
            case 58 : return 89.6f;
            case 59 : return 90.5f;
            case 60 : return 91.3f;
            case 61 : return 92.0f;
            case 62 : return 92.7f;
            case 63 : return 93.4f;
            case 64 : return 94.0f;
            case 65 : return 94.6f;
            case 66 : return 95.3f;
            case 67 : return 96.0f;
            case 68 : return 96.7f;
            case 69 : return 97.3f;
            case 70 : return 97.9f;
            case 71 : return 98.4f;
            case 72 : return 98.9f;
            case 73 : return 99.3f;
            case 74 : return 99.4f;
            case 75 : return 100.1f;
            case 76 : return 100.6f;
            case 77 : return 101.1f;
            case 78 : return 101.5f;
            case 79 : return 101.9f;
            case 80 : return 102.3f;
            case 81 : return 102.7f;
            case 82 : return 103.1f;
            case 83 : return 103.4f;
            case 84 : return 103.7f;
            case 85 : return 104.0f;
            case 86 : return 104.4f;
            case 87 : return 104.7f;
            case 88 : return 105.0f;
            case 89 : return 105.3f;
            case 90 : return 105.6f;
            default : return 75.4f;
        }
    }

    public static float getCarbon( int avg ){
        switch(avg){
            case 12 : return 30.1f;
            case 13 : return 32.6f;
            case 14 : return 35.1f;
            case 15 : return 37.7f;
            case 16 : return 40.2f;
            case 17 : return 42.7f;
            case 18 : return 45.2f;
            case 19 : return 47.7f;
            case 20 : return 50.2f;
            case 21 : return 52.3f;
            case 22 : return 54.4f;
            case 23 : return 56.5f;
            case 24 : return 58.5f;
            case 25 : return 60.5f;
            case 26 : return 62.6f;
            case 27 : return 64.7f;
            case 28 : return 66.8f;
            case 29 : return 68.8f;
            case 30 : return 70.8f;
            case 31 : return 72.9f;
            case 32 : return 75.0f;
            case 33 : return 77.1f;
            case 34 : return 79.2f;
            case 35 : return 81.2f;
            case 36 : return 83.3f;
            case 37 : return 85.4f;
            case 38 : return 87.5f;
            case 39 : return 89.5f;
            case 40 : return 91.5f;
            case 41 : return 93.4f;
            case 42 : return 95.3f;
            case 43 : return 97.2f;
            case 44 : return 99.1f;
            case 45 : return 101.1f;
            case 46 : return 103.0f;
            case 47 : return 104.9f;
            case 48 : return 106.8f;
            case 49 : return 108.7f;
            case 50 : return 110.6f;
            case 51 : return 111.8f;
            case 52 : return 113.0f;
            case 53 : return 114.2f;
            case 54 : return 115.4f;
            case 55 : return 116.5f;
            case 56 : return 117.7f;
            case 57 : return 118.9f;
            case 58 : return 120.1f;
            case 59 : return 121.3f;
            case 60 : return 122.4f;
            case 61 : return 123.3f;
            case 62 : return 124.2f;
            case 63 : return 125.1f;
            case 64 : return 126.0f;
            case 65 : return 126.9f;
            case 66 : return 127.8f;
            case 67 : return 128.7f;
            case 68 : return 129.6f;
            case 69 : return 130.5f;
            case 70 : return 131.3f;
            case 71 : return 131.9f;
            case 72 : return 132.5f;
            case 73 : return 133.1f;
            case 74 : return 133.7f;
            case 75 : return 134.3f;
            case 76 : return 134.9f;
            case 77 : return 135.5f;
            case 78 : return 136.1f;
            case 79 : return 136.7f;
            case 80 : return 137.2f;
            case 81 : return 137.6f;
            case 82 : return 138.0f;
            case 83 : return 138.4f;
            case 84 : return 138.8f;
            case 85 : return 139.2f;
            case 86 : return 139.6f;
            case 87 : return 140.0f;
            case 88 : return 140.4f;
            case 89 : return 140.8f;
            case 90 : return 141.2f;
            default : return 101.0f;
        }
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

}



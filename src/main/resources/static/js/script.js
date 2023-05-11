function Product(id_, name_, cpu_, ram_, gpu_, hardDrive_, screen_, port_, audio_, wireless_, webcam_, orderFunction_, size_, weight_, color_, battery_, os_, price_, amount_, images_) {
    let id = id_;
    let name = name_;
    let cpu = cpu_;
    let ram = ram_;
    let gpu = gpu_;
    let hardDrive = hardDrive_;
    let screen = screen_;
    let port = port_;
    let audio = audio_;
    let wireless = wireless_;
    let webcam = webcam_;
    let orderFunction = orderFunction_;
    let size = size_;
    let weight = weight_;
    let color = color_;
    let battery = battery_;
    let os = os_;
    let price = price_;
    let amount = amount_;
    let images = images_;

    this.getId = function()
    {
        return id;
    }
    this.setId = function(id_)
    {
        id = id_;
    }

    this.getName = function()
    {
        return name;
    }
    this.setName = function(name_)
    {
        name = name_;
    }

    this.getCPU = function()
    {
        return cpu;
    }
    this.setCPU = function(cpu_)
    {
        cpu = cpu_
    }

    this.getRam = function()
    {
        return ram;
    }
    this.setRam = function(ram_)
    {
        ram = ram_;
    }

    this.getGPU = function()
    {
        return gpu;
    }
    this.setGPU = function(gpu_)
    {
        gpu = gpu_;
    }

    this.getHardDrive = function()
    {
        return hardDrive;
    }
    this.setHardDrive = function(hardDrive_)
    {
        hardDrive = hardDrive_;
    }

    this.getScreen = function()
    {
        return screen;
    }
    this.setScreen = function(screen_)
    {
        screen = screen_;
    }

    this.getPort = function()
    {
        return port;
    }
    this.setPort = function(port_)
    {
        port = port_;
    }

    this.getAudio = function()
    {
        return audio;
    }
    this.setAudio = function(audio_)
    {
        audio = audio_;
    }

    this.getWireless = function()
    {
        return wireless;
    }
    this.setWireles = function(wireless_)
    {
        wireless = wireless_;
    }

    this.getWebcam = function()
    {
        return webcam;
    }
    this.setWebcam = function(webcam_)
    {
        webcam = webcam_;
    }

    this.getorderFunction = function()
    {
        return orderFunction;
    }
    this.setorderFunction = function(orderFunction_)
    {
        orderFunction = orderFunction_;
    }

    this.getSize = function()
    {
        return size;
    }
    this.setSize = function(size_)
    {
        size = size_;
    }

    this.getWeight = function()
    {
        return weight;
    }
    this.setWeight = function(weight_)
    {
        weight = weight_;
    }

    this.getColor = function()
    {
        return color;
    }
    this.setColor = function(color_)
    {
        color = color_;
    }

    this.getBattery = function()
    {
        return battery;
    }
    this.setBattery = function(battery_)
    {
        battery = battery_;
    }

    this.getOS = function()
    {
        return os;
    }
    this.setOS = function(os_)
    {
        os = os_;
    }

    this.getPrice = function()
    {
        return price;
    }
    this.setPrice = function(price_)
    {
        price = price_;
    }

    this.getAmount = function()
    {
        return amount;
    }
    this.setAmount = function(amount_)
    {
        amount = amount_;
    }

    this.getImages = function(index)
    {
        return images[index];
    }
    this.setImages = function(images_)
    {
        images = images_;
    }

    this.toString = function() {
        return "\n\t\tid: " + id + "\n\t\tname: " + name + "\n\t\tcpu: " + cpu + "\n\t\tram: " + ram + "\n\t\tgpu: " + gpu
            + "\n\t\thardDirve: " + hardDrive + "\n\t\tscreen: " + screen + "\n\t\tport: " + port + "\n\t\taudio: " + audio
            + "\n\t\twireless: " + wireless + "\n\t\twebcam: " + webcam + "\n\t\torderFunction: " + orderFunction
            + "\n\t\tsize: " + size + "\n\t\tweight: " + weight + "\n\t\tcolor: " + color + "\n\t\tbattery: " + battery
            + "\n\t\tos: " + os + "\n\t\tprice: " + price + "\n\t\tamount: " + amount + "\n\t\timages: " + images;
    }
}

function ProductList() {
    var productList = new Array();

    this.addProducts = function(products) {
        $.each(products, function(index, product) {
            productList.push(product);
        });
    }

    this.addProduct = function(product)
    {
        productList.push(product);
    }

    this.removeProduct = function(index) {
        productList.splice(index, 1);
    }

    this.changeProduct = function(product, index) {
        productList[index] = product;
    }

    this.getProduct = function(index) {
        return productList[index];
    }

    this.getProductById = function(id)
    {
        for(let i = 0; i < productList.length; i++)
            if(id == productList[i].getId())
                return productList[i];
    }

    this.getProductList = function()
    {
        return productList;
    }

    this.toString = function()
    {
        return productList;
    }
}

function Contact(name_, phoneNumber_, address_) {
    let name = name_;
    let phoneNumber = phoneNumber_;
    let address = address_;

    this.getName = function()
    {
        return name;
    }
    this.setName = function(name_)
    {
        name = name_;
    }

    this.getPhoneNumber = function()
    {
        return phoneNumber;
    }
    this.setPhoneNumber = function(phoneNumber_)
    {
        phoneNumber = phoneNumber_;
    }

    this.getAddress = function()
    {
        return address;
    }
    this.setAddress = function(address_)
    {
        address = address_;
    }

    this.toString = function()
    {
        return "\n\t\tname: " + name + "\n\t\tphoneNumber: " + phoneNumber + "\n\t\taddress: " + address;
    }
}

function ContactList() {
    let contactList = new Array();

    this.addContactToList = function(contact)
    {
        contactList.push(contact);
    }

    this.getContact = function(index)
    {
        return contactList[index];
    }

    this.getContactList = function()
    {
        return contactList;
    }

    this.toString = function()
    {
        let result = "";
        for(let i = 0; i < contactList.length; i++)
        {
            result += "\n\tContactList[" + i + "]: " + contactList[i].toString();
        }
        return result;
    }
}

function ProductInCart(productId_, amount_) {
    var productId = productId_;
    var amount = amount_;

    this.getProductId = function()
    {
        return productId;
    }
    this.setProductId = function(productId_)
    {
        productId = productId_;
    }

    this.getAmount = function()
    {
        return amount;
    }
    this.setAmount = function(amount_)
    {
        amount = amount_;
    }
}

function Cart() 
{
    let cart = new Array();

    this.addProduct = function(productInCart) {
        cart.push(productInCart);
    }

    this.removeProduct = function(index) {
        cart.splice(productInCart);
    }

    this.getCart = function()
    {
        return cart;
    }

    this.toString = function()
    {
        return "\n\tCart: " + cart;
    }
}

function LoveProducts() {
    var loveProducts = new Array();

    this.addProduct = function(productId)
    {
        loveProducts.push(productId);
    }

    this.removeProduct = function(index)
    {
        loveProducts.splice(index, 1);
    }

    this.getLoveProductList = function()
    {
        return loveProducts;
    }

    this.toString = function()
    {
        return "\n\tLoveProduct: " + loveProducts;
    }
}

function Order(id_, time_, contact_, note_, status_) {
    let id = id_;
    let time = time_;
    let contact = contact_;
    let productList = new ProductList();
    let note = note_;
    let status = status_;

    this.getId = function()
    {
        return id;
    }
    this.setId = function(id_)
    {
        id = id_;
    }

    this.getTime = function()
    {
        return time;
    }
    this.setTime = function(time_)
    {
        time = time_;
    }

    this.getContact = function()
    {
        return contact;
    }
    this.setContact = function(contact_)
    {
        contact = contact_;
    }

    this.addProduct = function(product_, amount_)
    {
        let product = product_;
        product.setAmount(amount_);
        productList.addProduct(product);
    }
    this.getProductList = function()
    {
        return productList;
    }

    this.getNote = function()
    {
        return note;
    }
    this.setNote = function(note_)
    {
        note = note_;
    }

    this.getStatus = function()
    {
        return status;
    }
    this.setStatus = function(status_)
    {
        status = status_;
    }

    this.toString = function()
    {
        return "\n\t\tid: " + id + "\n\t\ttime: " + time + "\n\t\tcontact: " + contact + "\n\t\tproductList: " 
            + productList.toString() + "\n\t\tnote: " + note;
    }
}

function OrderList() {
    let orderList = new Array();

    this.addOrderToList = function(order)
    {
        orderList.unshift(order);
    }

    this.getOrder = function(index)
    {
        return orderList[index];
    }

    this.getOrderList = function()
    {
        return orderList;
    }

    this.toString = function()
    {
        let result = "";
        for(let i = 0; i < orderList.length; i++)
        {
            result += "\n\tOrdertList[" + i + "]: " + orderList[i].toString();
        }
        return result;
    }
}

function Account(email_, password_) {
    let email = email_;
    let password = password_;
    let userName = new String();
    let phoneNumber = new String();
    let gender = new Number(); // 1:Nam 2:Nữ 3:Khác
    let dayOfBirth = new Date();
    let contacts = new ContactList();
    let cart = new Cart();
    let loveProducts = new LoveProducts();
    let orders = new OrderList();

    this.getEmail = function()
    {
        return email;
    }
    this.setEmail = function(email_)
    {
        email = email_;
    }
    
    this.setPassword = function(password_)
    {
        password = password_;
    }

    this.getUserName = function()
    {
        return userName;
    }
    this.setUserName = function(userName_)
    {
        userName = userName_;
    }

    this.getPhoneNumber = function()
    {
        return phoneNumber;
    }
    this.setPhoneNumber = function(phoneNumber_)
    {
        phoneNumber = phoneNumber_;
    }

    this.getGender = function()
    {
        return gender;
    }
    this.setGender = function(gender_)
    {
        gender = gender_;
    }

    this.getDayOfBirth = function()
    {
        return dayOfBirth;
    }
    this.setDayOfBirth = function(dayOfBirth_)
    {
        dayOfBirth = dayOfBirth_;
    }

    this.getContacts = function()
    {
        return contacts;
    }

    this.getCart = function() 
    {
        return cart;
    }

    this.getLoveProducts = function()
    {
        return loveProducts;
    }

    this.getOrders = function()
    {
        return orders;
    }

    this.toString = function()
    {
        return "\n\temail: " + email + "\n\tuserName: " + userName + contacts.toString() + cart.toString() + loveProducts.toString() + orders.toString();
    }
}

function AccountList() {
    let accountList = new Array();

    this.addAccountToList = function(account)
    {
        accountList.push(account);
    }

    this.getAccount = function(index)
    {
        return accountList[index];
    }

    this.toString = function()
    {
        let result = "";
        for(let i = 0; i < accountList.length; i++)
        {
            result += "AccountList[" + i + "]: " + accountList[i].toString() + "\n";
        }
        return result;
    }
}

function FormatPrice(n) {
    return n.replace(/\B(?=(\d{3})+(?!\d))/g, '.') + ' đ';
} 

//------------------------------------------------------------------------------------
//#region Khởi tạo bộ dữ liệu
let productList = new ProductList();
productList.addProducts([
        new Product(0, 
            "Laptop gaming Lenovo Legion 5P 15IMH05 82AY003EVN", 
            "Intel Core i5-10300H",
            "8GB DDR4 (2x SO-DIMM socket, up to 32GB SDRAM)", 
            "NVIDIA GeForce GTX 1650Ti + Intel UHD Graphics",
            "512GB SSD M.2 2280 PCIe NVMe", 
            "15.6inch FHD (1920x1080) WVA 300nits Anti-glare, 144Hz, 100% sRGB Gamut, Dolby Vision",
            "4x USB 3.2 Gen 1 (one Always On), 1x USB 3.2 Type-C Gen 1 (with the function of DisplayPort 1.2), 1xHDMI 2.0 1x Ethernet (RJ-45)",
            "HD Audio, Realtek ALC3287 codec /Dolby Atoms  for Gaming Certification, Harman speakers, 2W x 2",
            "WIFI 802.11ax 2x2, Bluetooth v5.0", 
            "Có", 
            "Bàn phím Fullsize RGB", 
            "363.06 x 259.61 x 23.57 (mm)", 
            "2.3 kg", 
            "Iron grey",
            "4 Cell 80Whr", 
            "Windows 10 Home",
            20990000,
            4,
            [("../img/product-images/lenovo-legion-5/37172_lenovo_legion_5_pro_16ach6h_ct1_02.png"),
             ("../img/product-images/lenovo-legion-5/Lenovo-Legion-0.jpeg")]),

        new Product(1, 
            "Laptop ASUS TUF Gaming F15 FX506LH HN002T", 
            "Intel Core i5-10300H 2.5GHz up to 4.5GHz 8MB",
            "8GB DDR4 2933MHz (2x SO-DIMM socket, up to 32GB SDRAM)", 
            "NVIDIA GeForce GTX 1650 4GB GDDR6 + Intel® UHD Graphics",
            "512GB SSD M.2 PCIE G3X2, 1x slot SATA3 2.5",
            "15.6 FHD (1920 x 1080) IPS, 144Hz, Wide View, 250nits, Narrow Bezel, Non-Glare with 45% NTSC, 63% sRGB",
            "2x Type-A USB 3.1 (Gen 1), 1x Type-A USB2.0, 1x RJ-45 LAN, 1x HDMI 2.0B, 1x COMBO audio jack",
            "DTS:X® Ultra",
            "WIFI 802.11AC (2X2), Bluetooth	v5.0",
            "HD 720p CMOS module",
            "Bàn phím 1-Zone RGB",
            "360 x 262 x 25.8 ~26.9 cm",
            "2.2 kg",
            "Gun Metal",
            "3 Cell 48WHr",
            "Windows 10 Home",
            21290000,
            2,
            [("../img/product-images/asus-tuf-f15/5268_asus_tuf_a15_fa506ih_al018t.jpg"),
             ("../img/product-images/asus-tuf-f15/ASUS-TUF-Gaming-A15-FA506QM-HN005T-6.jpg"),
             ("../img/product-images/asus-tuf-f15/637395003613911019_asus-tuf-fx506-xam-4.jpg")]),

        new Product(2,
            "Laptop gaming Acer Nitro 5 AN515 45 R0B6",
            "AMD Ryzen™ 7 5800H ( 3.2GHz up to 4.4GHz , 16Mb) Cores 8 , 16 Threads",
            "8GB DDR4 3200MHz (2x SO-DIMM socket, up to 32GB SDRAM)",
            "NVIDIA GeForce RTX 3060 6GB GDDR6 + AMD Radeon™ Graphics",
            "512GB SSD M.2 PCIE (Còn trống 1 khe SSD M.2 PCIE và 1 khe 2.5 SATA)",
            "15.6 FHD (1920 x 1080) IPS, 144Hz, Anti-Glare",
            "3x USB 3.1 Gen 1, 1x USB 3.2 Gen 2 Type C, 1x HDMI, RJ45",
            "Waves MaxxAudio®, Acer TrueHarmony™",
            "WIFI 802.11AX (2x2), Bluetooth	v5.0",
            "HD Webcam",
            "Bàn phím RGB 4 vùng",
            "363.4 x 255 x 23.9 mm",
            "2.20 kg",
            "Đen",
            "3 Cell 57.5WHr",
            "Windows 10 Home",
            18990000,
            5,
            [("../img/product-images/acer-nitro-5/636994883259708096_acer-nitro-5-2019-den-2.jpg"),
             ("../img/product-images/acer-nitro-5/acer-nitro-5-an515-55-5206-i5-nhq7nsv007-600x600.jpg"),
             ("../img/product-images/acer-nitro-5/acer-nitro-5_an515-55_rgb-kb_05.jpg")]),

        new Product(3,
            "Laptop Dell Inspiron 15 7501 X3MRY1",
            "Intel Core i7 10750H 2.6GHz, 12MB",
            "8GB onboard DDR4/ 2933MHz (x1 slot So-Dimm)",
            "NVIDIA GEFORCE GTX 1650Ti 4GB GDDR6",
            "512GB SSD PCIe (M.2 2230) – combo M.2 2230/2280",
            "15.6” inch diagonal Full HD (1920 x 1080) @60Hz Wide View Angle (WVA), Anti glare",
            "2 x USB 3.1 Gen 1 Type A ; 1 x USB Type C (port with Thunderbolt/DisplayPort/Power Delivery) ; 1 x HDMI ; 1 x Headphone/Microphone combo jack ; 1 x SSD (M.2 2280)",
            "Realtek High Definition Audio (Speaker : 2 x 2W)",
            "WIFI 802.11ac 1x1 WiFi, Bluetooth	v5.0",
            "HD Camera",
            "Bàn phím	RGB Backlight Keyboard",
            "356.1 x 234.5 x 18.9 (mm)",
            "1.851 kg",
            "Bạc",
            "3 Cell 56WHr",
            "Windows 10 Home",
            27890000,
            1,
            [("../img/product-images/dell-inspiron-15/10044092-dell-inspiron-5593-i5-1035g1-15-6-inch-n5I5513w-1_v9ip-ds_4nr4-09.jpg"),
             ("../img/product-images/dell-inspiron-15/30759_laptop_dell_inspiron_15_g3_3590_n5i5517w_black_1.jpg"),
             ("../img/product-images/dell-inspiron-15/in5593nt_cnb_00055lf110_gy.png")]),

        new Product(4,
            "Macbook Pro 13 MYDA2SA Touchbar M1/8GB/256GB/GPU 8-core (Bạc)",
            "Apple M1 chip with 8-core CPU and 8-core GPU",
            "8GB",
            "Apple M1 GPU 8 cores",
            "256GB SSD",
            "Retina 13.3 inch (2560x1600) IPS Led Backlit True Tone",
            "2 Thunderbolt / USB 4 ports with support for: Charging, DisplayPort, Thunderbolt 3 (up to 40Gb/s), USB 3.1 Gen 2 (up to 10Gb/s)",
            "Stereo speakers",
            "Wifi 802.11ac - Bluetooth 5.0",
            "720p HD",
            "Bàn phím	Magic Keyboard, Touchbar, có LED; Bảo mật dấu vân tay",
            "304 x 212 x 156 mm",
            "1.4 kg",
            "Bạc",
            "Built in Polymer, 58.2Whr Up to 17 hours wireless web; Up to 20 hours Apple TV app movie playback",
            "Mac OS",
            32090000,
            2,
            [("../img/product-images/macbook-pro-13/636372300548098379_macbook-pro-13-inch-128gb-2017-6.jpg"),
             ("../img/product-images/macbook-pro-13/71wl6bv6o0l._ac_sl1500__2985d7ee2751429789f3fcfc2332d4fc_master.jpg"),
             ("../img/product-images/macbook-pro-13/gia_macbook_pro_2020_re_nhat_7_d000d1ac34dc4402b32eb4a3115d6fe4.jpg"),
             ("../img/product-images/macbook-pro-13/macbook-pro-13-inch-256gb-2017-5.jpg"),
             ("../img/product-images/macbook-pro-13/macbook-pro-2020_bgls-lb.png")])
]);

let accountList = new AccountList();

let account = new Account("thanhtam@gmail.com", "thanhtam123");
account.setUserName("Nguyễn Thanh Tâm");
account.setPhoneNumber("0966002637");
account.setGender(1);
account.setDayOfBirth(new Date(2002, 10, 10));
account.getContacts().addContactToList(new Contact("Nguyễn Thanh Tâm", "0966002637", "Hưng Thành Đông, Long Hưng B, Lấp Vò, Đồng Tháp"));
account.getContacts().addContactToList(new Contact("Nguyễn Thị Mỹ Tiên", "0396010591", "Hưng Thành Đông, Lấp Vò, Đồng Tháp"));
account.getLoveProducts().addProduct(productList.getProduct(2).getId());
account.getLoveProducts().addProduct(productList.getProduct(3).getId());
account.getLoveProducts().addProduct(productList.getProduct(4).getId());
account.getLoveProducts().addProduct(productList.getProduct(0).getId());
account.getCart().addProduct(new ProductInCart(productList.getProduct(1).getId(), 2));
account.getCart().addProduct(new ProductInCart(productList.getProduct(3).getId(), 1));
accountList.addAccountToList(account);

let order = new Order();
order.setId("#12345678");
order.setTime("16/05/2021");
order.setContact(account.getContacts().getContact(1));
order.addProduct(productList.getProduct(0), 1);
order.addProduct(productList.getProduct(2), 1);
order.addProduct(productList.getProduct(4), 2);
order.setNote("Gọi điện trước khi giao hàng. Cho kiểm tra hàng");
order.setStatus("ĐANG XỬ LÍ");
accountList.getAccount(0).getOrders().addOrderToList(order);

let account1 = new Account("nguyenthithumo2018@gmail.com", "thumo123");
account1.setUserName("Nguyễn Thị Thu Mơ");
account1.setPhoneNumber("0398161252");
account1.setGender(2);
account1.setDayOfBirth(new Date(2002, 5, 4));
account1.getContacts().addContactToList(new Contact("Nguyễn Thị Thu Mơ", "0398161252", "Lấp Vò, Đồng Tháp"));
account1.getCart().addProduct(new ProductInCart(productList.getProduct(1).getId(), 2));
account1.getCart().addProduct(new ProductInCart(productList.getProduct(2).getId(), 2));
accountList.addAccountToList(account1);
//#endregion
//------------------------------------------------------------------------------------

let indexAccount = 0;
let currentAccount = accountList.getAccount(indexAccount);

$(document).ready(function() {
    $('#btnAccount span').html(currentAccount.getUserName());
});

//------------------------------------------------------------------------------------


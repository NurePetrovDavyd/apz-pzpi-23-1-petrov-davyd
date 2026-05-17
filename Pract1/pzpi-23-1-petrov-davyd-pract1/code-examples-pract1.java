// Файл: code-examples-pract1.java
// Приклад реалізації патерна Builder (Будівельник) мовою Java

// 1. Продукт (Product) - складний об'єкт, який ми створюємо
class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
        this.isGraphicsCardEnabled = isGraphicsCardEnabled;
    }

    public void setBluetoothEnabled(boolean isBluetoothEnabled) {
        this.isBluetoothEnabled = isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage
                + ", GraphicsCard=" + isGraphicsCardEnabled + ", Bluetooth=" + isBluetoothEnabled + "]";
    }
}

// 2. Абстрактний Будівельник (Builder)
interface ComputerBuilder {
    void buildCPU();
    void buildRAM();
    void buildStorage();
    void buildGraphicsCard();
    void buildBluetooth();
    Computer getComputer();
}

// 3. Конкретний Будівельник 1 (ConcreteBuilder) - Ігровий комп'ютер
class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void buildCPU() {
        computer.setCPU("Intel Core i9");
    }

    @Override
    public void buildRAM() {
        computer.setRAM("32GB DDR5");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("2TB NVMe SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCardEnabled(true);
    }

    @Override
    public void buildBluetooth() {
        computer.setBluetoothEnabled(true);
    }

    @Override
    public Computer getComputer() {
        return this.computer;
    }
}

// 4. Конкретний Будівельник 2 (ConcreteBuilder) - Офісний комп'ютер
class OfficeComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public OfficeComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void buildCPU() {
        computer.setCPU("Intel Core i3");
    }

    @Override
    public void buildRAM() {
        computer.setRAM("8GB DDR4");
    }

    @Override
    public void buildStorage() {
        computer.setStorage("512GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        // Офісному комп'ютеру дискретна відеокарта не потрібна
        computer.setGraphicsCardEnabled(false);
    }

    @Override
    public void buildBluetooth() {
        computer.setBluetoothEnabled(false);
    }

    @Override
    public Computer getComputer() {
        return this.computer;
    }
}

// 5. Директор (Director) - керує процесом покрокової побудови
class Director {
    private ComputerBuilder builder;

    public void setBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.buildCPU();
        builder.buildRAM();
        builder.buildStorage();
        builder.buildGraphicsCard();
        builder.buildBluetooth();
    }
}

// 6. Клієнтський код (Main)
public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        // Створення ігрового комп'ютера
        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director.setBuilder(gamingBuilder);
        director.constructComputer();
        Computer gamingComputer = gamingBuilder.getComputer();
        System.out.println("Gaming PC: " + gamingComputer);

        // Створення офісного комп'ютера
        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        director.setBuilder(officeBuilder);
        director.constructComputer();
        Computer officeComputer = officeBuilder.getComputer();
        System.out.println("Office PC: " + officeComputer);
    }
}
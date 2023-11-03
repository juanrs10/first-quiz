package org.velezreyes.quiz.question6;

public class VendingMachineImpl implements VendingMachine {

    private int totalCents;

    private VendingMachineImpl() {
        this.totalCents = 0;
    }

    public static VendingMachine getInstance() {
        return new VendingMachineImpl();
    }

    @Override
    public void insertQuarter() {
        totalCents += 25;
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        Drink selectedDrink = null;

        switch (name) {
            case "ScottCola":
                if (totalCents >= 75) {
                    selectedDrink = new DrinkImpl("ScottCola", true);
                    totalCents -= 75;
                } else {
                    throw new NotEnoughMoneyException();
                }
                break;

            case "KarenTea":
                if (totalCents >= 100) {
                    selectedDrink = new DrinkImpl("KarenTea", false);
                    totalCents -= 100;
                } else {
                    throw new NotEnoughMoneyException();
                }
                break;

            default:
                throw new UnknownDrinkException();
        }

        return selectedDrink;
    }

    private static class DrinkImpl implements Drink {
        private final String name;
        private final boolean fizzy;

        public DrinkImpl(String name, boolean fizzy) {
            this.name = name;
            this.fizzy = fizzy;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isFizzy() {
            return fizzy;
        }
    }
}

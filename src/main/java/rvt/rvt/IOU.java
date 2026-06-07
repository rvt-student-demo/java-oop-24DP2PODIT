package rvt;

import java.util.HashMap;

public class IOU {
    private HashMap<String, Double> debts;

    public IOU() {
        this.debts = new HashMap<>();
    }

    public void setSum(String toWhom, double amount) {
        this.debts.put(toWhom, amount);
    }

    public double howMuchDoIOweTo(String toWhom) {
        if (this.debts.containsKey(toWhom)) {
            return this.debts.get(toWhom);
        }

        return 0;
    }
}
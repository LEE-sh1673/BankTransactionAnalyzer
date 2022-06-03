package org.lee_sh1673;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date,
                           final double amount,
                           final String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override public String toString() {
        return "BankTransaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BankTransaction other = (BankTransaction) obj;

        return Double.compare(amount, other.amount) == 0
                && date.equals(other.date)
                && description.equals(other.description);
    }

    @Override public int hashCode() {
        return Objects.hash(date, amount, description);
    }
}

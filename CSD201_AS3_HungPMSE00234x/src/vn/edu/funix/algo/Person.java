package vn.edu.funix.algo;

public class Person implements Comparable<Person> {
    private String ID;
    private String name;
    /** Place of birth */
    private String pob;
    /** Day of birth */
    private String dob;

    public Person(String ID) {
        this.ID = ID;
    }

    public Person(String ID, String name, String pob, String dob) {
        this.ID = ID;
        this.name = name;
        this.pob = pob;
        this.dob = dob;
    }

    @Override
    public int compareTo(Person e) {
        return this.ID.compareTo(e.ID);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return this.compareTo((Person) obj) == 0;
        }

        return super.equals(obj);
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s %-10s", ID, name, dob, pob);
    }
}

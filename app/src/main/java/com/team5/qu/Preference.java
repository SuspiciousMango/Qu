package com.team5.qu;

public class Preference {
    private double weight;
    private String name;
    private String chosenOptions="";
    
    //potentially use 0 and 1 to parse data.
    public Preference(double weight, String name, String chosenOptions){
        this.weight=weight;
        this.name=name;
        this.chosenOptions=chosenOptions;
    }
    /**
     * @return the chosenOptions
     */
    public String getChosenOptions() {
        return chosenOptions;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }
    /**
     * @param chosenOptions the chosenOptions to set
     */
    public void setChosenOptions(String chosenOptions) {
        this.chosenOptions = chosenOptions;
    }
    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "["+name+","+weight+","+chosenOptions+"]";
    }
@Override
protected Preference clone() throws CloneNotSupportedException {
    // TODO Auto-generated method stub
    return new Preference(weight, name, chosenOptions);
}
}
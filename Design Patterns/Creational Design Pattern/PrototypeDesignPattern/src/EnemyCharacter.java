public class EnemyCharacter implements CharacterPrototype{

    private String type;
    private int health;
    private int attackPower;

    //Simulate complex and time consuming constructor
    public EnemyCharacter(String type, int health, int attackPower){
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
    }

    //Setters to make specific changes in the new cloned object
    public void setType(String type) {
        this.type = type;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public CharacterPrototype clone() {
        System.out.println("Cloning the " + type + " character...");
        return new EnemyCharacter(this.type, this.health, this.attackPower);
    }

    @Override
    public void displayStats() {
        System.out.println("Enemy Type: " + type +
                "\nAttack Power: " + attackPower +
                "\nHealth: " + health + "\n");
    }
}

public class SimpleCharacter implements Character{
    @Override
    public String getCharacterName() {
        return "Alex";
    }

    @Override
    public String getCharacterRole() {
        return "Peasant";
    }

    @Override
    public String getArmourType() {
        return "No Armour";
    }

    @Override
    public double getHealth() {
        return 100;
    }

    @Override
    public String getWeapon() {
        return "Stick";
    }

    @Override
    public double getAttackPower() {
        return 5;
    }
}

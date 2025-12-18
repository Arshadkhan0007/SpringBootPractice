public class ThiefCharacter extends CharacterDecorator{
    public ThiefCharacter(Character decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public String getCharacterName() {
        return "Toby";
    }

    @Override
    public String getCharacterRole() {
        return "Thief";
    }

    @Override
    public String getArmourType() {
        return "Leather Armour";
    }

    @Override
    public double getHealth() {
        return 125;
    }

    @Override
    public String getWeapon() {
        return "Dagger";
    }

    @Override
    public double getAttackPower() {
        return 14.3;
    }
}

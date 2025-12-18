public class KnightCharacter extends CharacterDecorator{
    public KnightCharacter(Character decoratedCharacter) {
        super(decoratedCharacter);
    }

    @Override
    public String getCharacterName() {
        return "Chadimus Maximus";
    }

    @Override
    public String getCharacterRole() {
        return "Knight";
    }

    @Override
    public String getArmourType() {
        return "Metal Armour";
    }

    @Override
    public double getHealth() {
        return 250.0;
    }

    @Override
    public String getWeapon() {
        return "Sword";
    }

    @Override
    public double getAttackPower() {
        return 25.6;
    }
}

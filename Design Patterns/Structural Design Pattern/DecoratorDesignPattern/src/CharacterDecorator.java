public abstract class CharacterDecorator implements Character{
    protected final Character decoratedCharacter;

    public CharacterDecorator(Character decoratedCharacter) {
        this.decoratedCharacter = decoratedCharacter;
    }

    @Override
    public String getCharacterName() {
        return decoratedCharacter.getCharacterName();
    }

    @Override
    public String getCharacterRole() {
        return decoratedCharacter.getCharacterRole();
    }

    @Override
    public String getArmourType() {
        return decoratedCharacter.getArmourType();
    }

    @Override
    public double getHealth() {
        return decoratedCharacter.getHealth();
    }

    @Override
    public String getWeapon() {
        return decoratedCharacter.getWeapon();
    }

    @Override
    public double getAttackPower() {
        return decoratedCharacter.getAttackPower();
    }
}

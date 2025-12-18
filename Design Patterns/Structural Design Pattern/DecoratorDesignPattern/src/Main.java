public class Main{
    public static void main(String[] args){
        Character character = new SimpleCharacter();
        System.out.println(
                "Name: " + character.getCharacterName()
                + "\nRole: " + character.getCharacterRole()
                + "\nArmour Type: " + character.getArmourType()
                + "\nHealth: " + character.getHealth()
                + "\nWeapon: " + character.getWeapon()
                + "\nAttack Power: " + character.getAttackPower()
                + "\n"
                );

        character = new KnightCharacter(character);
        System.out.println(
                "Name: " + character.getCharacterName()
                + "\nRole: " + character.getCharacterRole()
                + "\nArmour Type: " + character.getArmourType()
                + "\nHealth: " + character.getHealth()
                + "\nWeapon: " + character.getWeapon()
                + "\nAttack Power: " + character.getAttackPower()
                + "\n"
        );

        character = new ThiefCharacter(character);
        System.out.println(
                "Name: " + character.getCharacterName()
                + "\nRole: " + character.getCharacterRole()
                + "\nArmour Type: " + character.getArmourType()
                + "\nHealth: " + character.getHealth()
                + "\nWeapon: " + character.getWeapon()
                + "\nAttack Power: " + character.getAttackPower()
                + "\n"

        );
    }
}
public class Game {
    CharacterPrototype enemyCharacter = new EnemyCharacter("EnemyType", 100, 25);

    public EnemyCharacter createZombie(){
        EnemyCharacter zombie = (EnemyCharacter) enemyCharacter.clone();

        zombie.setType("Zombie");
        zombie.setHealth(100);
        zombie.setAttackPower(25);

        return zombie;
    }

    public EnemyCharacter createSkeleton(){
        EnemyCharacter skeleton = (EnemyCharacter) enemyCharacter.clone();

        skeleton.setType("Skeleton");
        skeleton.setHealth(70);
        skeleton.setAttackPower(25);

        return skeleton;
    }
}

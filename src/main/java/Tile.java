public class Tile {
    private Owner owner;

    public Tile() {
        this.owner = Owner.NONE;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if (this.owner != Owner.NONE) {
            System.out.println("owner already set, err");
            return;
        }
        this.owner = owner;
    }
}

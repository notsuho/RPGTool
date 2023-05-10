package fi.rpgtool.data;

/**
 * Yksinkertainen dataluokka tiedon tallentamiseen. Tämänhetkinen toteutus lienee itsestään selvä.
 */
public class Pair<L, R> {

    public L left;
    public R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

}

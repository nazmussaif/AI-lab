package lab_1.missionaries_and_cannibals;

class State{
    private int ml;
    private int cl;
    private int mr;
    private int cr;
    private char bs;

    State(int ml, int cl, int mr, int cr, char bs){
        this.ml = ml;
        this.cl = cl;
        this.mr = mr;
        this.cr = cr;
        this.bs = bs;
    }

    State m(int n) {
        State tmp;
        if (bs == 'l') {
            tmp = new State(ml - n, cl, mr + n, cr, 'r');
        } else {
            tmp = new State(ml + n, cl, mr - n, cr, 'l');
        }
        if (tmp.is_valid() && (n == 1 || n == 2)) return tmp;
        else return null;
    }

    State c(int n){
        State tmp;
        if(bs == 'l'){
            tmp = new State(ml, cl-n, mr, cr+n, 'r');
        }
        else{
            tmp = new State(ml, cl+n, mr, cr-n, 'l');
        }
        if (tmp.is_valid() && (n == 1 || n == 2)) return tmp;
        else return null;
    }

    State mc(){
        State tmp;
        if(bs == 'l'){
            tmp = new State(ml-1, cl-1, mr+1, cr+1, 'r');
        }
        else{
            tmp = new State(ml+1, cl+1, mr-1, cr-1, 'l');
        }
        if(tmp.is_valid()) return tmp;
        else return null;
    }

    boolean goal(){
        return ml == 0 && cl == 0;
    }

    private boolean is_valid(){
        return (ml >= 0 && mr >= 0 && cl >= 0 && cr >= 0 && (ml >= cl || ml == 0) && (mr >= cr || mr == 0));
    }

    public String toString(){
        return  "->> " + "ml = " + ml + " cl = " + cl + " mr = " + mr + " cr = " + cr + " side = " + bs;
    }

    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State c = (State) obj;
            return ml == c.ml && mr == c.mr && cl == c.cl && cr == c.cr && bs == c.bs;
        }
        return false;
    }
}
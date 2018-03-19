package lab_1.jug;

class State{
    private static long cap1, cap2, target;
    private long one, two;

    State(long o, long t){
        one = o;
        two = t;
    }

    static void setParameters(long c1, long c2, long t){
        cap1 = c1;
        cap2 = c2;
        target = t;
    }

    State fill(int n){
        if(n == 1) return new State(cap1, two);
        else return new State(one, cap2);
    }

    State empty(int n){
        if(n == 1) return new State(0, two);
        else return new State(one, 0);
    }

    State transfer(int from){
        if(from == 1){
            long amount = Math.min(one, cap2-two);
            return new State(one-amount, two+amount);
        }
        else{
            long amount = Math.min(two, cap1 - one);
            return new State(one+amount, two-amount);
        }
    }

    boolean goal(){
        return one == target || two == target;
    }
    public String toString(){
        return  "->> " + "one = " + one + " two = " + two;
    }
}

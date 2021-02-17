class Expr {
    public double eval(double x, double y) {
        return 0;
    }
}

class X extends Expr {
    public double eval(double x, double y) {
        return x;
    }

    public String toString() {
        return ("X");
    }
}

class Y extends Expr {
    public double eval(double x, double y) {
        return y;
    }

    public String toString() {
        return ("Y");
    }
}

class Sin extends Expr {
    Expr op;

    Sin(Expr o) {
        op = o;
    }

    public double eval(double x, double y) {
        return (Math.sin(Math.PI * op.eval(x, y)));
    }

    public String toString() {
        return ("sin(Pi*" + op + ")");
    }
}

class Cos extends Expr {
    Expr op;

    Cos(Expr o) {
        op = o;
    }

    public double eval(double x, double y) {
        return Math.cos(Math.PI * op.eval(x, y));
    }

    public String toString() {
        return ("cos(Pi*" + op + ")");
    }
}

class Moyenne extends Expr {
    Expr op1;
    Expr op2;

    Moyenne(Expr o1, Expr o2) {
        op1 = o1;
        op2 = o2;
    }

    public double eval(double x, double y) {
        return (op1.eval(x, y) + op2.eval(x, y)) / 2;
    }

    public String toString() {
        return ("(" + op1 + "+" + op2 + ")/2");
    }
}

class Mult extends Expr {
    Expr op1;
    Expr op2;

    Mult(Expr o1, Expr o2) {
        op1 = o1;
        op2 = o2;
    }

    public double eval(double x, double y) {
        return op1.eval(x, y) * op2.eval(x, y);
    }

    public String toString() {
        return (op1 + "*" + op2);
    }
}
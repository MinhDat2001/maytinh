package com.mycompany.maytinh;

import java.util.Stack;

/**
 *
 * @author datd6
 */
public class tinhtoan {
    private double X=0;

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }
    // ghep hai stack de tao bieu thuc co con tro hien thi
    public String ghepCoConTro(Stack<String> st1, Stack<String> st2){
        Stack<String> st1cp=(Stack<String>)st1.clone();
        Stack<String> st2cp=(Stack<String>)st2.clone();
        st1cp.push("|");
        String res="";
        while(st1cp.size()>0){
            st2cp.push(st1cp.pop());
        }
        while(st2cp.size()>0){
            res=res.concat(st2cp.pop());
        }
        return res;
    }
    // ghep hai stack de tao bieu thuc khong co con tro hien thi
    public String ghepKhongConTro(Stack<String> st1, Stack<String> st2){
        Stack<String> st1cp=(Stack<String>)st1.clone();
        Stack<String> st2cp=(Stack<String>)st2.clone();
        String res="";
        while(st1cp.size()>0){
            st2cp.push(st1cp.pop());
        }
        while(st2cp.size()>0){
            res=res.concat(st2cp.pop());
        }
        return res;
    }
    // tinh giai thua
    public long giaiThua(String x){
        long n=Long.parseLong(x);
        if(n<0) return 0;
        long res=1;
        for (int i = 1; i <=n; i++) {
            res*=i;
        }
        return res;
    }
    // kiem tra xem ky tu co phai la phep toan +,-,*,/
    public boolean laDau(char x){
        if(x=='+'||x=='-'||x=='*'||x=='/')
            return true;
        return false;
    }
    public boolean ktBieuThuc(String bieuThuc){
        if(laDau(bieuThuc.charAt(bieuThuc.length()-1))) return true;
        if(laDau(bieuThuc.charAt(0))&&bieuThuc.charAt(0)!='-') return true;
        int demM=0, demD=0;
        for (int i = 0; i < bieuThuc.length(); i++) {
            if(bieuThuc.charAt(0)==')') demD++;
            if(bieuThuc.charAt(0)=='(') demM++;
            if(laDau(bieuThuc.charAt(i))&&
                    laDau(bieuThuc.charAt(i+1))&&bieuThuc.charAt(i+1)!='-')
                return true;
        }
        if(demD!=demM) return true;
        return false;
    }
    // tinh luy thua
    public double luyThua(String x, String y){
        double a=Double.parseDouble(x);
        double b=Double.parseDouble(y);
        double res=(Math.pow(a, b));
        return res;
    }
    // tim phan du cua phep chia
    public int chiaDu(String x, String y){
        int a=Integer.parseInt(x);
        int b=Integer.parseInt(y);
        return a%b;
    }
    // tim can bac 2
    public double CanBac2(String x){
        x=tinh(x);
        double res=Double.parseDouble(x);
        return (Math.sqrt(res));
    }
    //tinh sin
    public double sin(String x){
        x=tinh(x);
        double d=Double.parseDouble(x);
        double radian=Math.toRadians(d);
        double res=(int)(Math.sin(radian)*1000000000);
        res=(res/1000000000);
        return res;
    }
    //tinh cos
    public double cos(String x){
        x=tinh(x);
        double d=Double.parseDouble(x);
        double radian=Math.toRadians(d);
        double res=(int)(Math.cos(radian)*1000000000);
        res=(res/1000000000);
        return res;
    }
    // tinh tan
    public double tan(String x){
        x=tinh(x);
        double d=Double.parseDouble(x);
        double radian=Math.toRadians(d);
        double res=(int)(Math.tan(radian)*1000000000);
        res=(res/1000000000);
        return res;
    }
    // thay cac hang so trong bieu thuc
    public String thayHangSo(String bieuThuc){
        //hang so
        try {
            for (int i = 0; i < bieuThuc.length(); i++) {
                if (bieuThuc.charAt(i) == 'p') {
                    bieuThuc = bieuThuc.replace("pi", "3.141592653");
                }
                if (bieuThuc.charAt(i) == 'e') {
                    bieuThuc = bieuThuc.replace("e", "2.718281828");
                }
                if (bieuThuc.charAt(i) == 'X') {
                    String tmp = Double.toString(X);
                    bieuThuc = bieuThuc.replace("X", tmp);
                }
            }
            return bieuThuc;
        }
        catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh toan trong dau ngoac
    public String tinhtrongNgoac(String bieuThuc){
        //dau ngoac don ------- test:4+(5+(6+7))
        try {
            for (int i = 0; i < bieuThuc.length(); i++) {
                if ((bieuThuc.charAt(i) == '(' && i == 0) || (bieuThuc.charAt(i) == '(' &&
                        bieuThuc.charAt(i - 1) != 't' && bieuThuc.charAt(i - 1) != 'n' && bieuThuc.charAt(i - 1) != 's')) {
                    int dem = 1;
                    String tmp1 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (bieuThuc.charAt(j) != ')' || dem > 0)) {
                        if (bieuThuc.charAt(j) == '(') dem++;
                        if (bieuThuc.charAt(j) == ')') dem--;
                        if (dem == 0) break;
                        tmp1 += bieuThuc.charAt(j);
                        j++;
                    }
                    String ans = tinh(tmp1);
                    tmp1 = '(' + tmp1;
                    tmp1 = tmp1.concat(")");
//                System.out.println("ans: "+ans+" tmp1: "+tmp1);
                    bieuThuc = bieuThuc.replace(tmp1, ans);
//                System.out.println("bieu thuc: "+bieuThuc);
                }
            }
            return bieuThuc;
        } catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh sin cos tan
    public String tinhSinCos(String bieuThuc){
        try {
            for (int i = 2; i < bieuThuc.length(); i++) {
                // sin
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '(' && bieuThuc.charAt(i - 1) == 'n' && bieuThuc.charAt(i - 2) == 'i') {
                    int dem = 1;
                    String tmp1 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (bieuThuc.charAt(j) != ')' || dem > 0)) {
                        if (bieuThuc.charAt(j) == '(') dem++;
                        if (bieuThuc.charAt(j) == ')') dem--;
                        if (dem == 0) break;
                        tmp1 += bieuThuc.charAt(j);
                        j++;
                    }
                    double ans = sin(tmp1);
                    tmp1 = "sin(" + tmp1;
                    tmp1 = tmp1.concat(")");
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
                //tan
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '(' && bieuThuc.charAt(i - 1) == 'n' && bieuThuc.charAt(i - 2) == 'a') {
                    int dem = 1;
                    String tmp1 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (bieuThuc.charAt(j) != ')' || dem > 0)) {
                        if (bieuThuc.charAt(j) == '(') dem++;
                        if (bieuThuc.charAt(j) == ')') dem--;
                        if (dem == 0) break;
                        tmp1 += bieuThuc.charAt(j);
                        j++;
                    }
                    double ans = tan(tmp1);
                    tmp1 = "tan(" + tmp1;
                    tmp1 = tmp1.concat(")");
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
//                    System.out.println("bieu thuc: "+bieuThuc);
                }
                //cos
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '(' && bieuThuc.charAt(i - 1) == 's' && bieuThuc.charAt(i - 2) == 'o') {
                    int dem = 1;
                    String tmp1 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (bieuThuc.charAt(j) != ')' || dem > 0)) {
                        if (bieuThuc.charAt(j) == '(') dem++;
                        if (bieuThuc.charAt(j) == ')') dem--;
                        if (dem == 0) break;
                        tmp1 += bieuThuc.charAt(j);
                        j++;
                    }
                    double ans = cos(tmp1);
//                System.out.println("ans: "+ans);
                    tmp1 = "cos(" + tmp1;
                    tmp1 = tmp1.concat(")");
//                System.out.println("tmp1: "+tmp1);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
//                System.out.println("bieu thuc: "+bieuThuc);
                }
            }
            return bieuThuc;
        }catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh mu va tinh can
    public String tinhMuCan(String bieuThuc){
        //mu & can
        try {
            for (int i = 1; i < bieuThuc.length() - 1; i++) {
                // mu
                if (bieuThuc.charAt(i) == '^') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) ||
                            bieuThuc.charAt(j) == '.' || bieuThuc.charAt(i + 1) == '-')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
                    j = i - 1;
                    while (j >= 0 && (Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.')) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    double ans = luyThua(tmp1, tmp2);
                    tmp1 = tmp1.concat("^");
                    tmp1 = tmp1.concat(tmp2);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
                // can
                if (i > 2 && bieuThuc.charAt(i) == '(' && bieuThuc.charAt(i - 1) == 't') {
                    int dem = 1;
                    String tmp1 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (bieuThuc.charAt(j) != ')' || dem > 0)) {
                        if (bieuThuc.charAt(j) == '(') dem++;
                        if (bieuThuc.charAt(j) == ')') dem--;
                        if (dem == 0) break;
                        tmp1 += bieuThuc.charAt(j);
                        j++;
                    }
                    double ans = CanBac2(tmp1);
                    tmp1 = "sqrt(" + tmp1;
                    tmp1 = tmp1.concat(")");
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
//                    System.out.println("bieu thuc: "+bieuThuc);
                }
            }
            return bieuThuc;
        }catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh giai thua
    public String tinhGiaiThua(String bieuThuc){
        //giai thua
        try {
            for (int i = 1; i < bieuThuc.length(); i++) {
                if (bieuThuc.charAt(i) == '!') {
                    String tmp1 = "";
                    int j = i - 1;
                    while (j >= 0 && (Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.')) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    long ans = giaiThua(tmp1);
                    tmp1 = tmp1.concat("!");
                    bieuThuc = bieuThuc.replace(tmp1, Long.toString(ans));
                }
            }
            return bieuThuc;
        }catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh toan cac phep toan nhan chia va phan tram
    public String tinhNhanChia(String bieuThuc){
        try {
            for (int i = 1; i < bieuThuc.length() - 1; i++) {
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '*') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) ||
                            bieuThuc.charAt(j) == '.' || bieuThuc.charAt(i + 1) == '-')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
//                System.out.println(tmp2);
                    j = i - 1;
                    while (j >= 0 && ((Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.') ||
                            (bieuThuc.charAt(j) == '-') && (j == 0 || j > 1 && laDau(bieuThuc.charAt(j - 1))))) {
//                    System.out.println("kt");
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
//                    System.out.println(tmp1);
                    double t1 = Double.parseDouble(tmp1);
                    double t2 = Double.parseDouble(tmp2);
                    double ans = t1 * t2;
                    tmp1 = tmp1.concat("*");
                    tmp1 = tmp1.concat(tmp2);
//                System.out.println(tmp1);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
//                System.out.println(bieuThuc);
                }
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '/') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) ||
                            bieuThuc.charAt(j) == '.' || bieuThuc.charAt(i + 1) == '-')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
                    j = i - 1;
                    while (j >= 0 && ((Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.') ||
                            (bieuThuc.charAt(j) == '-') && (j == 0 || j > 1 && laDau(bieuThuc.charAt(j - 1))))) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    double t1 = Double.parseDouble(tmp1);
                    double t2 = Double.parseDouble(tmp2);
                    if(t2==0) return "ERROR";
                    double ans = t1 / t2;
                    tmp1 = tmp1.concat("/");
                    tmp1 = tmp1.concat(tmp2);
//                System.out.println(tmp1);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '%') {
                    String tmp1 = "";
                    int j = i - 1;
                    while (j >= 0 && ((Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.') ||
                            (bieuThuc.charAt(j) == '-') && (j == 0 || j > 1 && laDau(bieuThuc.charAt(j - 1))))) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    double t1 = Double.parseDouble(tmp1);
                    double ans = t1 / 100;
                    tmp1 = tmp1.concat("%");
//                System.out.println(tmp1);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
            }
            return bieuThuc;
        }catch (NumberFormatException exception){
            return "ERROR";
        }
    }
    // tinh toan phep chia du
    public String tinhChiaLayDu(String bieuThuc){
        //chia lay du
        try {
            for (int i = 1; i < bieuThuc.length() - 1; i++) {
                if (bieuThuc.charAt(i) == ':') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
                    j = i - 1;
                    while (j >= 0 && (Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.')) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    long ans = chiaDu(tmp1, tmp2);
                    tmp1 = tmp1.concat(":");
                    tmp1 = tmp1.concat(tmp2);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
            }
            return bieuThuc;
        }catch (Exception exception){
            return "ERROR";
        }
    }
    // tinh toan cac phep toan cong tru
    public String tinhCongTru(String bieuThuc){
        //cong tru
        try {
            for (int i = 1; i < bieuThuc.length() - 1; i++) {
                // cong
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '+') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) ||
                            bieuThuc.charAt(j) == '.' || bieuThuc.charAt(i + 1) == '-')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
                    j = i - 1;
                    while (j >= 0 && ((Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.') ||
                            (bieuThuc.charAt(j) == '-') && (j == 0 || j > 1 && laDau(bieuThuc.charAt(j - 1))))) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    double t1 = Double.parseDouble(tmp1);
                    double t2 = Double.parseDouble(tmp2);
                    double ans = t1 + t2;
                    tmp1 = tmp1.concat("+");
                    tmp1 = tmp1.concat(tmp2);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
                //tru
                if (i < bieuThuc.length() - 1 && bieuThuc.charAt(i) == '-') {
                    String tmp1 = "", tmp2 = "";
                    int j = i + 1;
                    while (j < bieuThuc.length() && (Character.isDigit(bieuThuc.charAt(j)) ||
                            bieuThuc.charAt(j) == '.' || bieuThuc.charAt(i + 1) == '-')) {
                        tmp2 += bieuThuc.charAt(j);
                        j++;
                    }
                    j = i - 1;
                    while (j >= 0 && ((Character.isDigit(bieuThuc.charAt(j)) || bieuThuc.charAt(j) == '.') ||
                            (bieuThuc.charAt(j) == '-') && (j == 0 || j > 1 && laDau(bieuThuc.charAt(j - 1))))) {
                        tmp1 = bieuThuc.charAt(j) + tmp1;
                        j--;
                    }
                    double t1 = Double.parseDouble(tmp1);
                    double t2 = Double.parseDouble(tmp2);
                    double ans = t1 - t2;
                    tmp1 = tmp1.concat("-");
                    tmp1 = tmp1.concat(tmp2);
                    bieuThuc = bieuThuc.replace(tmp1, Double.toString(ans));
                }
            }
            return bieuThuc;
        }catch (NumberFormatException exception){
            return "ERROR";
        }
    }
    // ham tinh toan chinh
    public String tinh(String bieuThuc){
        try {//4*(sin(45)-1)
            if(ktBieuThuc(bieuThuc)) return "ERROR";
            bieuThuc = thayHangSo(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhtrongNgoac(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhSinCos(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhMuCan(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhGiaiThua(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhNhanChia(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhChiaLayDu(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
            bieuThuc = tinhCongTru(bieuThuc);
            if("ERROR".equals(bieuThuc)) return bieuThuc;
//            System.out.println("----"+bieuThuc);
            return bieuThuc;
        }
        catch (Exception exception){
            return "ERROR";
        }
    }
}


public class Test {
    public static void main(String[] args) {
        Ceasar cipher = new Ceasar(1);
        System.out.println(cipher.encrypt("din mammammama ligger pa pizza"));
        System.out.println(cipher.decrypt(cipher.encrypt("din mammammama ligger pa pizza")));
    }
}

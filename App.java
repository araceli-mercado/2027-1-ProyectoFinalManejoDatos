public class App {
    public static void main(String[] args) throws Exception {
        ABB abb = new ABB(-3);
        int[] data = {-50,-49,-46,-44,-43,-40,-35,-32,-16,-15,-14,-10,-4,-3,5,9,13,18,20,27,28,29,33,35,36,43,44};
        ABB nuevo = new ABB();

        limpiaConsola();

        System.out.println();

        abb.inserta(49);
        abb.inserta(-20);
        abb.inserta(28);
        abb.inserta(36);
        abb.inserta(-48);
        abb.inserta(10);
        abb.inserta(34);
        abb.inserta(-17);
        abb.inserta(6);
        abb.inserta(-27);
        abb.inserta(15);
        abb.inserta(45);
        abb.inserta(50);
        abb.inserta(-13);
        abb.inserta(-5);
        abb.inserta(2);
        abb.inserta(26);
        abb.inserta(-29);
        abb.inserta(3);
        abb.inserta(48);
        abb.inserta(22);

        System.out.println("Original:");
        System.out.println("pre: " + abb.preOrden());
        System.out.println("in: " + abb.inOrden());
        System.out.println("post: " + abb.posOrden());
        System.out.println();

        // abb.balanceaDSW();
        abb.balanceaConArreglo();

        System.out.println("después de balancear:");
        System.out.println("pre: " + abb.preOrden());
        System.out.println("in: " + abb.inOrden());
        System.out.println("post: " + abb.posOrden());

               
        System.out.println();
    }

    public static void limpiaConsola() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

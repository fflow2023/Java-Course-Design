public class Main {
    public static void main(String[] args) {
        // 创建并启动 StudentManagerApp 应用程序
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentManagerApp();
            }
        });
    }
}

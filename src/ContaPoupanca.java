import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
        rendimento();
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Poupanca ===");
        super.imprimirInfosComuns();
    }

    private void rendimento() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            double yield = saldo * 0.024;
            double newSaldo = saldo + yield;
            saldo = newSaldo;

            System.out.println(String.format(
                    "==================== " +
                            "\nRendimento da poupanca.. + %.2f \nSaldo atualizado: %.2f\n" +
                    "====================", yield, getSaldo()));
        };
        scheduler.scheduleAtFixedRate(task, 30, 600, TimeUnit.SECONDS);
    }
}

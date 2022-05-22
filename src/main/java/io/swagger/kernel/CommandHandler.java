package io.swagger.kernel;

public interface CommandHandler<C extends Command, R> {
    R handle(C command) throws InterruptedException;
}

package mainsource.system.game.gameparts;

public abstract class ActionInput {
    public abstract void fold();
    public abstract void call();
    public abstract void bet(int val);
    public abstract void raise(int val);
}

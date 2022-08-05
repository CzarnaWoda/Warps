package pl.blackwater.warps.timer;

public interface TimerCallback<E>
{
    void success(E p0);
    
    void error(E p0);
}

package virtuozo.interfaces;

public interface Layout<L extends Layout<L>> {
  L attach();
  
  L detach();
}

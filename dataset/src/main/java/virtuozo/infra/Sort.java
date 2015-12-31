package virtuozo.infra;


public enum Sort {
  NONE {

    @Override
    public int direction() {
      return 0;
    }

    @Override
    public Sort reverse() {
      return ASC;
    }
  },
  DESC {

    @Override
    public int direction() {
      return -1;
    }

    @Override
    public Sort reverse() {
      return ASC;
    }
  },
  ASC {

    @Override
    public int direction() {
      return 1;
    }

    @Override
    public Sort reverse() {
      return DESC;
    }
  };

  public abstract int direction();

  public abstract Sort reverse();
}
package virtuozo.interfaces;

import virtuozo.interfaces.Icon;

public class FontAwesomeAssets implements Assets {

  @Override
  public Icon previousIcon() {
    return FontAwesome.CARET_LEFT;
  }

  @Override
  public Icon nextIcon() {
    return FontAwesome.CARET_RIGHT;
  }

  @Override
  public Icon upIcon() {
    return FontAwesome.CARET_UP;
  }

  @Override
  public Icon downIcon() {
    return FontAwesome.CARET_DOWN;
  }

  @Override
  public Icon successIcon() {
    return FontAwesome.CHECK;
  }

  @Override
  public Icon warningIcon() {
    return FontAwesome.WARNING;
  }

  @Override
  public Icon errorIcon() {
    return FontAwesome.REMOVE;
  }

  @Override
  public Icon searchIcon() {
    return FontAwesome.SEARCH;
  }

  @Override
  public Icon clearIcon() {
    return FontAwesome.ERASER;
  }

  @Override
  public Icon uploadIcon() {
    return FontAwesome.UPLOAD;
  }
}

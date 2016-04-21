package virtuozo.interfaces;

import virtuozo.interfaces.Icon;

public class GhyphiconAssets implements Assets {

  @Override
  public Icon previousIcon() {
    return Glyphicon.CHEVRON_LEFT;
  }

  @Override
  public Icon nextIcon() {
    return Glyphicon.CHEVRON_RIGHT;
  }

  @Override
  public Icon successIcon() {
    return Glyphicon.OK;
  }

  @Override
  public Icon warningIcon() {
    return Glyphicon.WARNING_SIGN;
  }

  @Override
  public Icon errorIcon() {
    return Glyphicon.REMOVE;
  }
  
  @Override
  public Icon searchIcon() {
    return Glyphicon.FILTER;
  }
  
  @Override
  public Icon clearIcon() {
    return Glyphicon.REMOVE;
  }
  
  @Override
  public Icon downIcon() {
    return Glyphicon.CHEVRON_DOWN;
  }
  
  @Override
  public Icon upIcon() {
    return Glyphicon.CHEVRON_UP;
  }
  
  @Override
  public Icon uploadIcon() {
    return Glyphicon.UPLOAD;
  }
}
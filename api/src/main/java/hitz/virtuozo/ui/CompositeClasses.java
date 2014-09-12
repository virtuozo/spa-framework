/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;

public final class CompositeClasses implements UIClasses {
  private UIClasses[] classes;

  public CompositeClasses(UIClasses... classes) {
    if (classes == null) {
      throw new IllegalArgumentException("Classes should not be null");
    }
    this.classes = classes;
  }

  @Override
  public UIClasses append(UIClass... classes) {
    State.APPEND.execute(this.classes, classes);
    return this;
  }

  @Override
  public UIClasses toggle(UIClass clazz) {
    State.TOGGLE.execute(this.classes, clazz);
    return this;
  }

  @Override
  public UIClasses set(UIClass... classes) {
    State.SET.execute(this.classes, classes);
    return this;
  }

  @Override
  public boolean contains(UIClass clazz) {
    return this.contains(clazz.name());
  }

  @Override
  public UIClasses remove(UIClass... classes) {
    State.REMOVE.execute(this.classes, classes);
    return this;
  }

  @Override
  public UIClasses append(String... classes) {
    State.APPEND.execute(this.classes, classes);
    return this;
  }

  @Override
  public UIClasses toggle(String clazz) {
    State.TOGGLE.execute(this.classes, clazz);
    return this;
  }

  @Override
  public UIClasses set(String... classes) {
    State.SET.execute(this.classes, classes);
    return this;
  }

  @Override
  public boolean contains(String clazz) {
    for (UIClasses classes : this.classes) {
      if (classes.contains(clazz)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public UIClasses remove(String... classes) {
    State.REMOVE.execute(this.classes, classes);
    return this;
  }

  enum State {
    APPEND {
      @Override
      public void execute(UIClasses classes, UIClass... clazz) {
        classes.append(clazz);
      }

      @Override
      void execute(UIClasses classes, String... clazz) {
        classes.append(clazz);
      }
    },
    REMOVE {
      @Override
      void execute(UIClasses classes, String... clazz) {
        classes.remove(clazz);
      }

      @Override
      void execute(UIClasses classes, UIClass... clazz) {
        classes.remove(clazz);
      }
    },
    SET {
      @Override
      void execute(UIClasses classes, String... clazz) {
        classes.set(clazz);
      }

      @Override
      void execute(UIClasses classes, UIClass... clazz) {
        classes.set(clazz);
      }
    },
    TOGGLE {
      @Override
      void execute(UIClasses classes, String... clazz) {
        classes.toggle(clazz[0]);
      }

      @Override
      void execute(UIClasses classes, UIClass... clazz) {
        classes.toggle(clazz[0]);
      }
    };

    public void execute(UIClasses[] classes, UIClass... clazz) {
      for (UIClasses uiClasses : classes) {
        this.execute(uiClasses, clazz);
      }
    }

    public void execute(UIClasses[] classes, String... clazz) {
      for (UIClasses uiClasses : classes) {
        this.execute(uiClasses, clazz);
      }
    }

    abstract void execute(UIClasses classes, UIClass... clazz);

    abstract void execute(UIClasses classes, String... clazz);
  }
}

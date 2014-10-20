package hitz.virtuozo.infra;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HashCollection {
  private Set<Hash> values = new HashSet<Hash>();

  public HashCollection add(Hash hash) {
    this.values.add(hash);
    return this;
  }

  public HashCollection addAll(Collection<Hash> hashes) {
    this.values.addAll(hashes);
    return this;
  }

  public HashCollection remove(Hash hash) {
    this.values.remove(hash);
    return this;
  }

  public HashCollection removeAll(Collection<Hash> hashes) {
    this.values.removeAll(hashes);
    return this;
  }

  public boolean contains(Hash hash) {
    return this.values.contains(hash);
  }

  public HashCollection clear() {
    this.values.clear();
    return this;
  }

  public Iterable<Hash> values() {
    return Collections.unmodifiableSet(values);
  }
}
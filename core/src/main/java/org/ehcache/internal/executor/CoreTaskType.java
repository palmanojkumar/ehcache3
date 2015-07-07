package org.ehcache.internal.executor;

public enum CoreTaskType implements TaskType {

  EVENT_NOTIFICATION, WRITE_BEHIND, DISK_WRITE, STATISTICAL, BOOTSTRAP;
}

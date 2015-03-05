/*
 * Copyright (c) 2012-2015 Spotify AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spotify.dns;

import java.util.Set;

/**
 * TODO: document
 *
 * @param <T>
 */
public interface ChangeNotifier<T> {

  /**
   * Get the current set of records.
   */
  Set<T> current();

  /**
   * Set a listener to be called when the set of records change.
   *
   * @param listener The listener to set.
   * @param fire     Fire the notification event immediately. Can be used to ensure that no updates
   *                 are missed when setting the listener.
   */
  void setListener(Listener<T> listener, boolean fire);

  /**
   * Close this {@link ChangeNotifier}, releasing any resources allocated. Once closed, no more
   * {@link Listener} events will be fired. Implementations of {@link ChangeNotifier} are not
   * allowed to throw checked exceptions from close().
   */
  void close();

  /**
   * A listener which will be called when the set of records change.
   */
  interface Listener<T> {

    /**
     * Signal that set of records changed.
     *
     * @param changeNotification An object containing details about the change.
     */
    void onChange(ChangeNotification<T> changeNotification);
  }

  interface ChangeNotification<T> {
    Set<T> current();
    Set<T> previous();
  }
}

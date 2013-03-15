/*
 * Created on Dec 21, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.assert4j.core.assertions.internal.maps;

import static org.assert4j.core.assertions.data.MapEntry.entry;
import static org.assert4j.core.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.assert4j.core.assertions.test.Maps.mapOf;
import static org.assert4j.core.assertions.test.TestData.someInfo;
import static org.assert4j.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assert4j.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import java.util.Map;

import org.assert4j.core.assertions.core.AssertionInfo;
import org.assert4j.core.assertions.internal.Maps;
import org.assert4j.core.assertions.internal.MapsBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link Maps#assertHasSize(AssertionInfo, Map, int)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class Maps_assertHasSize_Test extends MapsBaseTest {

  @Test
  public void should_pass_if_size_of_actual_is_equal_to_expected_size() {
    Map<?, ?> actual = mapOf(entry("name", "Yoda"), entry("job", "Yedi Master"));
    maps.assertHasSize(someInfo(), actual, 2);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    maps.assertHasSize(someInfo(), null, 8);
  }

  @Test
  public void should_fail_if_size_of_actual_is_not_equal_to_expected_size() {
    AssertionInfo info = someInfo();
    Map<?, ?> actual = mapOf(entry("name", "Yoda"), entry("job", "Yedi Master"));
    try {
      maps.assertHasSize(info, actual, 8);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveSize(actual, actual.size(), 8));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
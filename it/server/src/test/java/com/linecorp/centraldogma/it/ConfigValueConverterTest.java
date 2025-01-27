/*
 * Copyright 2024 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.linecorp.centraldogma.it;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import com.linecorp.centraldogma.server.CentralDogmaConfig;

class ConfigValueConverterTest {

    @Test
    void convert() {
        assertThat(CentralDogmaConfig.convertValue("invalid space prefix:invalid", "property"))
                .isEqualTo("invalid space prefix:invalid");
        assertThat(CentralDogmaConfig.convertValue("valid_prefix:value", "property"))
                .isEqualTo("valid_value");
    }

    @SetEnvironmentVariable(key = "ZONE", value = "ZONE_A")
    @SetEnvironmentVariable(key = "MY_ZONE", value = "ZONE_B")
    @Test
    void environmentVariable() {
        assertThat(CentralDogmaConfig.convertValue("env:ZONE", "zone")).isEqualTo("ZONE_A");
        assertThat(CentralDogmaConfig.convertValue("env:MY_ZONE", "zone")).isEqualTo("ZONE_B");
    }
}

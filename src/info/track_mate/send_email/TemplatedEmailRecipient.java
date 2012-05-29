/**
 * Copyright 2000-2012 TrackMate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.track_mate.send_email;

import java.util.Map;

/**
 * Object representing a participant for a templated message, including any substitution parameters.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class TemplatedEmailRecipient {

  private String recipientAddress;

  private Map<String, Object> recipientParams;

  /**
   * @return the recipientAddress
   */
  public String getRecipientAddress() {
    return recipientAddress;
  }

  /**
   * @param recipientAddress the recipientAddress to set
   */
  public void setRecipientAddress(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  /**
   * @return the recipientParams
   */
  public Map<String, Object> getRecipientParams() {
    return recipientParams;
  }

  /**
   * @param recipientParams the recipientParams to set
   */
  public void setRecipientParams(Map<String, Object> recipientParams) {
    this.recipientParams = recipientParams;
  }

}

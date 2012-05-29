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

import info.track_mate.util.Config;
import javax.mail.PasswordAuthentication;

/**
 * Mail authenticator for an SMTP server.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class SMTPAuthenticator extends javax.mail.Authenticator {

  /** Logger for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SMTPAuthenticator.class);

  private static final String CONFIG_FILENAME_DEFAULT = "mailserver.properties";
  private Config mailserverConfig;

  public SMTPAuthenticator() {
    this(CONFIG_FILENAME_DEFAULT);
  }

  public SMTPAuthenticator(String configFileName) {
    mailserverConfig = new Config(configFileName);
  }

  @Override
  public PasswordAuthentication getPasswordAuthentication() {
    String username = null;
    String password = null;
    try {
      username = mailserverConfig.getProperty("mail.smtp.username");
      password = mailserverConfig.getProperty("mail.smtp.password");
    } catch (Exception e) {
      logger.error("Failed to get SMTP authentication credentials", e);
    }
    return new PasswordAuthentication(username, password);
  }
}

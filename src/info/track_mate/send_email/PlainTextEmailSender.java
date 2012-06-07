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

import java.util.Collection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.ubiquity.info.track_mate.send_email.SMTPAuthenticator;

/**
 * Utility class for sending plain-text emails.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class PlainTextEmailSender extends AbstractEmailSender implements MessageSender {

  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlainTextEmailSender.class);

  /**
   * Creates a new PlainTextEmailSender using the default config values.
   */
  public PlainTextEmailSender() {
    super();
  }

  /**
   * Creates a new PlainTextEmailSender using the specified properties
   * @param props The configuration properties to initialise the mail system with.
   */
  public PlainTextEmailSender(Properties props){
      super(props);
  }

  /**
   * Creates a new PlainTextEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  public PlainTextEmailSender(String configFilename) {
    super(configFilename);
  }

  /**
   * Send an email.
   * @param recipientAddress The address to send the email to.
   * @param messageSubject The subject of the email.
   * @param messageBody The body of the email.
   * @throws Exception If the email couldn't be sent.
   */
  @Override
  public void sendMessage(Collection<String> recipientAddresses, Collection<String> ccAddresses, Collection<String> bccAddresses, String messageSubject,
          String messageBody, Collection<MessageAttachment> messageAttachments) throws Exception {
    Session session = Session.getInstance(mailServerConfig, new SMTPAuthenticator());
    logger.info("Got session: " + session);
    if (logger.isDebugEnabled()) {
      session.setDebug(true);
    }
    MimeMessage message = new MimeMessage(session);
    try {
      // 'To'
      if (recipientAddresses != null) {
        for (String recipientAddress : recipientAddresses) {
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
        }
      }
      // 'CC'
      if (ccAddresses != null) {
        for (String ccAddress : ccAddresses) {
          message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
        }
      }
      // 'BCC'
      if (bccAddresses != null) {
        for (String bccAddress : bccAddresses) {
          message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress));
        }
      }

      String emailContent = messageBody;
      if (logger.isDebugEnabled()) {
        logger.debug("Email content: " + emailContent);
      }

      // Email content.
      message.setSubject(messageSubject);
      message.setText(emailContent);
      Transport.send(message);
    } catch (MessagingException e) {
      throw new Exception("Cannot send email. ", e);
    }
  }

  //------ Private Helpers ------

}

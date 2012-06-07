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

import info.track_mate.util.FileUtils;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * A MessageSender capable of sending multipart email messages.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class MultipartEmailSender extends AbstractEmailSender implements MessageSender {

  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MultipartEmailSender.class);

  /**
   * Creates a new MultipartEmailSender using the default config values.
   */
  public MultipartEmailSender() {
    super();
  }

  /**
   * Creates a new MultipartEmailSender using the specified properties.
   * @param props The configuration properties to initialise the mail system with.
   */
  public MultipartEmailSender(Properties props){
      super(props);
  }

  /**
   * Creates a new MultipartEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  public MultipartEmailSender(String configFilename) {
    super(configFilename);
  }

  /**
   * Creates a new MultipartEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  public MultipartEmailSender(String configFileDir, String configFilename) {
    super(configFileDir, configFilename);
  }

  /**
   * Creates a new MultipartEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  public MultipartEmailSender(File configFile) {
    super(configFile);
  }

  @Override
  public void sendMessage(Collection<String> recipientAddresses, Collection<String> ccAddresses, Collection<String> bccAddresses, String messageSubject,
      String messageBody, Collection<MessageAttachment> messageAttachments) throws Exception {
    String plainText = convertHTMLToPlainText(messageBody);
    sendEmail(recipientAddresses, ccAddresses, bccAddresses, messageSubject, plainText, messageBody, messageAttachments);
  }

  /**
   * Send a multipart email.
   * @param recipientAddresses Collection of 'To' addresses.
   * @param ccAddresses Collection of 'CC' addresses.
   * @param bccAddresses Collection of 'BCC' addresses.
   * @param messageSubject Message subject.
   * @param plainTextMessage The plain-text version of the message.
   * @param htmlMessage The HTML version of the message.
   * @param fileAttachments Map of file-based attachments &lt;Content-ID, fileName&gt;. Note that the Content-ID (cid) is user-defined, but must match that used
   * in the email body if e.g. attaching images to display within the email.
   * @throws Exception If the message couldn't be prepared / couldn't be sent.
   */
  public void sendEmail(Collection<String> recipientAddresses, Collection<String> ccAddresses, Collection<String> bccAddresses, String messageSubject,
      String plainTextMessage, String htmlMessage, Collection<MessageAttachment> messageAttachments) throws Exception {
    // Create the Session object
    Session session = Session.getInstance(mailServerConfig, new SMTPAuthenticator(configFilePath));
    if (logger.isInfoEnabled()) {
      logger.info("Got session: " + session);
    }
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

      // Subject.
      message.setSubject(messageSubject);

      // Build up the mutipart content.
      MimeMultipart rootMultipartMessage = new MimeMultipart("alternative");
      MimeBodyPart rootBodyPart = new MimeBodyPart();
      MimeMultipart htmlAndImagesMultipartMessage = new MimeMultipart("related");

      // The plain-text part of the message.
      BodyPart textBodyPart = new MimeBodyPart();
      textBodyPart.setText(plainTextMessage);
      rootMultipartMessage.addBodyPart(textBodyPart);

      // The HTML part of the message.
      BodyPart htmlBodyPart = new MimeBodyPart();
      htmlBodyPart.setContent(htmlMessage, "text/html");
      htmlAndImagesMultipartMessage.addBodyPart(htmlBodyPart);

      // Add any attachments.
      if (messageAttachments != null) {
        for (MessageAttachment messageAttachment : messageAttachments) {
          BodyPart filePart = new MimeBodyPart();
          String filePath = messageAttachment.getFilePath();
          String filename = messageAttachment.getFilename();
          String fullFilePath;
          if (filePath == null) {
            fullFilePath = FileUtils.getClasspathFilePathFromName(filename);
          } else {
            fullFilePath = filePath + "/" + filename;
          }
          DataSource fileDataSource = new FileDataSource(fullFilePath);
          filePart.setDataHandler(new DataHandler(fileDataSource));
          filePart.setHeader("Content-ID", "<" + messageAttachment.getContentID() + ">");
          filePart.setHeader("Content-Type", messageAttachment.getContentType());
          htmlAndImagesMultipartMessage.addBodyPart(filePart);
        }
      }
      // Add the multipart to the body.
      rootBodyPart.setContent(htmlAndImagesMultipartMessage);
      rootMultipartMessage.addBodyPart(rootBodyPart);

      //  Add the multipart content to the message
      message.setContent(rootMultipartMessage);
      message.setHeader("X-Mailer", "info.track_mate.send_email.MultipartEmailSender");
      message.setSentDate(new Date());

      Transport.send(message);
    } catch (MessagingException e) {
      throw new Exception("Cannot send email. ", e);
    }
  }

  private String convertHTMLToPlainText(String htmlString) {
    String plainText = new String(htmlString);
    plainText = plainText.replaceAll("&nbsp;", "&");
    plainText = plainText.replaceAll("&apos;", "'");
    plainText = plainText.replaceAll("&pound;", "&");
    plainText = plainText.replaceAll("&lt;", "<");
    plainText = plainText.replaceAll("&gt;", ">");
    plainText = plainText.replaceAll("&copy;", "(c)");
    plainText = plainText.replaceAll("\n", " ");
    plainText = plainText.replaceAll("<a .*?href=\"(.*?)\".*?>(.*?)</a>", "$2 [$1]");
    plainText = plainText.replaceAll("<img .*?alt=\"(.*?)\".*?>", " $1 ");
    plainText = plainText.replaceAll("<br */?>", "\n").replaceAll("</tr>", "\n").replaceAll("</table>", "\n").replaceAll("<p>", "\n\n").replaceAll("</p>", "\n\n");
    plainText = plainText.replaceAll("<.*?>", "").replaceAll(" {2,}", " ");
    if (logger.isDebugEnabled()) {
      logger.debug("Created plain-text version of email:\n" + plainText);
    }
    return plainText;
  }
}

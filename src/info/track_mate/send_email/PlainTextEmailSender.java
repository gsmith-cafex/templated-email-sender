/*
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Copyright (c) 2000-2010 TrackMate.
 */
package info.track_mate.send_email;

import java.util.Collection;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    Session session = Session.getDefaultInstance(mailServerConfig, new SMTPAuthenticator());
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

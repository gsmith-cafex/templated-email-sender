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

import info.track_mate.util.Config;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author gareth
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

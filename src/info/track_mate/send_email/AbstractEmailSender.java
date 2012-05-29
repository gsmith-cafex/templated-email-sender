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

import info.track_mate.templating.Template;
import info.track_mate.templating.TemplateEngine;
import info.track_mate.templating.TemplatingHelper;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public abstract class AbstractEmailSender implements MessageSender {

  /** Logger instance for this class. */
  private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AbstractEmailSender.class);

  /** The mail server config properties. */
  protected Properties mailServerConfig = new Properties();
  /** The name of the default mailserver file. */
  private static final String CONFIG_FILENAME = "mailserver.properties";
  /** The path to the file used as the config file. */
  protected String configFilePath;

  /**
   * Creates a new AbstractEmailSender using the default config values.
   */
  protected AbstractEmailSender() {
    loadConfig(null, CONFIG_FILENAME, null);
  }

  /**
   * Creates a new AbstractEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  protected AbstractEmailSender(String configFilename) {
    loadConfig(null, configFilename, null);
  }

  /**
   * Creates a new AbstractEmailSender
   * @param configFilename The name of the config file to initialise the mail system with.
   */
  protected AbstractEmailSender(String configFileDir, String configFilename) {
    if (configFilename == null) {
      loadConfig(configFileDir, CONFIG_FILENAME, null);
    } else {
      loadConfig(configFileDir, configFilename, null);
    }
  }

  /**
   * Creates a new AbstractEmailSender
   * @param configFile The config file to use when initialising the mail system.
   */
  protected AbstractEmailSender(File configFile) {
    loadConfig(null, null, configFile);
  }

  @Override
  public abstract void sendMessage(Collection<String> recipientAddresses, Collection<String> ccAddresses, Collection<String> bccAddresses, String messageSubject,
      String messageBody, Collection<MessageAttachment> messageAttachments) throws Exception;

  @Override
  public void sendTemplatedMessage(Collection<TemplatedEmailRecipient> recipientDetails, TemplatedMessage templatedMessage) throws Exception {
    TemplateEngine templateEngine = TemplatingHelper.getTemplateEngine(templatedMessage.getTemplatingEngine(), templatedMessage.getTemplateFilePath());
    Template template = templateEngine.getTemplate(templatedMessage.getTemplateFileName());

    for (TemplatedEmailRecipient recipientDetail : recipientDetails) {
      String templateOutput = templateEngine.processTemplate(template, recipientDetail.getRecipientParams());

      Collection<String> recipientAddresses = new ArrayList<String>();
      recipientAddresses.add(recipientDetail.getRecipientAddress());
      sendMessage(recipientAddresses, null, null, templatedMessage.getSubject(), templateOutput, templatedMessage.getAttachments());
    }
  }

  // ------ Private Helpers ------

  /**
   * Loads the specified config file.
   * @param configFilename The name of the config file used to initialise the mail system.
   */
  private void loadConfig(String configFileDir, String configFilename, File configFile) {
    InputStream input = null;
    File file;
    if (configFile != null) {
      file = configFile;
    } else if (configFileDir != null) {
      file = new File(configFileDir + "/" + configFilename);
    } else {
      file = new File(configFilename);
    }
    
    if (file.exists()) {
      configFilePath = file.getAbsolutePath();
      try {
        input = new BufferedInputStream(new FileInputStream(file));
      } catch (Exception e) {
        logger.error("Failed to read file: " + file.getAbsolutePath(), e);
      }
      
    } else {
      try {
        URL url = Thread.currentThread().getContextClassLoader().getResource(configFilename);
        if (url != null) {
          input = new BufferedInputStream(url.openStream());
          configFilePath = configFilename;
        }
      } catch (Exception e) {
        logger.error("Failed to load resource from classpath: " + configFilename, e);
      }
    }

    if (input == null) {
      throw new NullPointerException("No such resource found: " + configFilename);
    }

    try {
      mailServerConfig.load(input);
      logger.debug("Loaded mail config file: " + (configFilename == null? file.getName() : configFilename));
    } catch (IOException e) {
      logger.error("Cannot open and load mail server properties file.", e);
    } finally {
      try {
        if (input != null) {
          input.close();
        }
      } catch (IOException e) {
        logger.error("Cannot close mail server properties file.", e);
      }
    }
  }

}

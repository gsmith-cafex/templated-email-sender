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

import info.track_mate.templating.TemplatingHelper.TemplatingEngine;
import java.util.Collection;

/**
 * Represents a message template.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class TemplatedMessage {
  private String subject;
  private String templateFilePath;
  private String templateFileName;
  private Collection<MessageAttachment> attachments;
  private TemplatingEngine templatingEngine;

  /**
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * @param subject the subject to set
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * @return the templateFileName
   */
  public String getTemplateFileName() {
    return templateFileName;
  }

  /**
   * @param templateFileName the templateFileName to set
   */
  public void setTemplateFileName(String templateFileName) {
    this.templateFileName = templateFileName;
  }

  /**
   * @return the attachments
   */
  public Collection<MessageAttachment> getAttachments() {
    return attachments;
  }

  /**
   * @param attachments the attachments to set
   */
  public void setAttachments(Collection<MessageAttachment> attachments) {
    this.attachments = attachments;
  }

  /**
   * @return the templatingEngine
   */
  public TemplatingEngine getTemplatingEngine() {
    return templatingEngine;
  }

  /**
   * @param templatingEngine the templatingEngine to set
   */
  public void setTemplatingEngine(TemplatingEngine templatingEngine) {
    this.templatingEngine = templatingEngine;
  }

  /**
   * @return the templateFilePath
   */
  public String getTemplateFilePath() {
    return templateFilePath;
  }

  /**
   * @param templateFilePath the templateFilePath to set
   */
  public void setTemplateFilePath(String templateFilePath) {
    this.templateFilePath = templateFilePath;
  }
}

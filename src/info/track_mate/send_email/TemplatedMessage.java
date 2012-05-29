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

import info.track_mate.templating.TemplatingHelper.TemplatingEngine;
import java.util.Collection;

/**
 *
 * @author gareth
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

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

/**
 *
 * @author gareth
 */
public class MessageAttachment {
  private String filePath;
  private String filename;
  private String contentID;
  private String contentType;

  public MessageAttachment() {
  }

  /**
   * Creates a new MessageAttachment.
   * @param filename The name of the file to attach. The classpath will be scanned for this filename.
   * @param contentID The Content-ID (cid) to set for the attachment.
   * @param contentType The Content-Type of the attachment.
   */
  public MessageAttachment(String filename, String contentID, String contentType) {
    this.filename = filename;
    this.contentID = contentID;
    this.contentType = contentType;
  }

  /**
   * Creates a new MessageAttachment.
   * @param filePath The path to the file. If this is null then the classpath will be scanned for the file instead.
   * @param filename The name of the file to attach.
   * @param contentID The Content-ID (cid) to set for the attachment.
   * @param contentType The Content-Type of the attachment.
   */
  public MessageAttachment(String filePath, String filename, String contentID, String contentType) {
    this.filePath = filePath;
    this.filename = filename;
    this.contentID = contentID;
    this.contentType = contentType;
  }

  /**
   * @return the filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * @param filename the name to set
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * @return the contentID
   */
  public String getContentID() {
    return contentID;
  }

  /**
   * @param contentID the contentID to set
   */
  public void setContentID(String contentID) {
    this.contentID = contentID;
  }

  /**
   * @return the contentType
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * @param contentType the contentType to set
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * @return the filePath
   */
  public String getFilePath() {
    return filePath;
  }

  /**
   * @param filePath the filePath to set
   */
  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}

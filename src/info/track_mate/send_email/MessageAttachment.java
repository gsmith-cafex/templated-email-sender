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

/**
 * An attachment to include within a multipart message.
 * @author Gareth Smith <gareth@track-mate.info>
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

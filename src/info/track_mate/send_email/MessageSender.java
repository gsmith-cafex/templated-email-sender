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

/**
 * Represents a class capable of sending electronic messages.
 * @author Gareth Smith <gareth@track-mate.info>
 */
public interface MessageSender {

  /**
   * Send an email to the specified addresses with the specified content.
   * @param recipientAddresses The addresses for the message to be addressed to.
   * @param ccAddresses The addresses for the message to be carbon-copied to.
   * @param bccAddresses The addresses for the message to be 'blind' carbon-copied to.
   * @param messageSubject The subject of the message.
   * @param messageBody The body of the message.
   * @throws Exception If the message couldn't be sent.
   */
  void sendMessage(Collection<String> recipientAddresses, Collection<String> ccAddresses, Collection<String> bccAddresses, String messageSubject,
      String messageBody, Collection<MessageAttachment> messageAttachments) throws Exception;

  void sendTemplatedMessage(Collection<TemplatedEmailRecipient> recipientAddresses, TemplatedMessage templatedMessage) throws Exception;
}

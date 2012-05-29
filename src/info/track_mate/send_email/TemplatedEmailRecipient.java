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

import java.util.Map;

/**
 *
 * @author Gareth Smith <gareth@track-mate.info>
 */
public class TemplatedEmailRecipient {

  private String recipientAddress;

  private Map<String, Object> recipientParams;

  /**
   * @return the recipientAddress
   */
  public String getRecipientAddress() {
    return recipientAddress;
  }

  /**
   * @param recipientAddress the recipientAddress to set
   */
  public void setRecipientAddress(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  /**
   * @return the recipientParams
   */
  public Map<String, Object> getRecipientParams() {
    return recipientParams;
  }

  /**
   * @param recipientParams the recipientParams to set
   */
  public void setRecipientParams(Map<String, Object> recipientParams) {
    this.recipientParams = recipientParams;
  }

}
